package com.car.CarRenting.services

import com.car.CarRenting.dto.request.AuthenticationRequest
import com.car.CarRenting.dto.request.RegistrationRequest
import com.car.CarRenting.dto.response.AuthenticationResponse
import com.car.CarRenting.entity.*
import com.car.CarRenting.entity.access.Role
import com.car.CarRenting.entity.access.Token
import com.car.CarRenting.entity.account.CarOwner
import com.car.CarRenting.entity.account.Customer
import com.car.CarRenting.entity.account.User
import com.car.CarRenting.enums.EmailTemplateName
import com.car.CarRenting.enums.RoleEnum
import com.car.CarRenting.repository.*
import com.car.CarRenting.security.JwtService
import jakarta.mail.MessagingException
import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.MailSendException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.SecureRandom
import java.time.LocalDateTime

@Service
@RequiredArgsConstructor
class AuthenticationService(
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val customerRepository: CustomerRepository,
    private val carOwnerRepository: CarOwnerRepository,
    private val tokenRepository: TokenRepository,
    private val emailService: EmailService,
    private val authenticationManager: AuthenticationManager,
    @Value("\${application.mailing.frontend.activation-url}")
    private val activationUrl: String,
    private val jwtService: JwtService,

    private val logger: Logger = LoggerFactory.getLogger(AuthenticationService::class.java)
) {


    @Throws(MessagingException::class)
    @Transactional
    fun register(request: RegistrationRequest) {

        logger.debug("Registering user with email: {}", request.email)

        // Create or get role
        val role = roleRepository.findByName(request.role!!).orElseGet {
            val newRole = Role().apply {
                name = request.role
                createdAt = LocalDateTime.now()
                modifiedAt = LocalDateTime.now()
            }
            roleRepository.save(newRole)
        }

        logger.debug("Role found or created: {}", role.name)

        // Create user without setting createdBy
        var user = User().apply {
            userName = request.userName
            gender = request.gender!!
            address = request.address
            city = request.city
            country = request.country
            phoneNumber = request.phoneNumber
            firstname = request.firstname
            lastname = request.lastname
            email = request.email
            password = passwordEncoder.encode(request.password)
            accountLocked = false
            enabled = false
            isDeleted = false
            roles.add(role)

        }
        userRepository.save(user)

        logger.debug("User created with ID: {}", user.id)

        user.createdBy = user.id
        userRepository.save(user) // Save the user again to update createdBy

//        if (role.createdBy == 0L) {
//            role.createdBy = user.id
//            roleRepository.save(role) // Update role with correct createdBy
//        }

        // Handle specific role-related entities
        when (request.role) {
            RoleEnum.CUSTOMER -> {
                val customer = Customer().apply {
                    this.user = user
                }
                customerRepository.save(customer)
                user.customer = customer
            }

            RoleEnum.CAR_OWNER -> {
                val carOwner = CarOwner().apply {
                    this.user = user
                }

                carOwner.copyFrom(user)
                carOwnerRepository.save(carOwner)
                user.carOwner = carOwner
                carOwner.createdBy = carOwner.id
                user.carOwner = carOwner
            }

            else -> {
                // Handle other roles or throw an exception
            }
        }

        // Save user again to ensure all relationships are updated
        userRepository.save(user)
        logger.debug("User registration completed for ID: {}", user.id)


        sendValidationEmail(user)
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        try {
            logger.debug("Authenticating user with email: {}", request.email)
            val auth = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    request.email,
                    request.password
                )
            )
            logger.debug("Successfully authenticated user: {}", request.email)

            val claims = mutableMapOf<String, Any>()
            val user = auth.principal as User
            claims["fullName"] = user.fullname()
            val jwtToken = jwtService.generateToken(claims, user)
            return AuthenticationResponse().apply { token = jwtToken }


            // Return appropriate response
        } catch (e: AuthenticationException) {
            logger.error("Authentication failed for user: {}", request.email, e)
            throw e
        } catch (e: Exception) {
            logger.error("Unexpected error during authentication: {}", e.message, e)
            throw e
        }
    }


    @Throws(MessagingException::class)
    @Transactional
    fun activateAccount(token: String) {

        //Check first if the user has already enabled his account then proceed

        val savedToken: Token = tokenRepository.findByToken(token).orElseThrow {
            RuntimeException("Invalid token")
        }


        if (savedToken.user?.enabled == true) {
            throw RuntimeException("Already enabled")
        } else {

            if (LocalDateTime.now().isAfter(savedToken.expiresAt)) {
                sendValidationEmail(savedToken.user!!)
                throw RuntimeException("Activation token has expired. A new token has been sent to the same email.")
            }

            val user = userRepository.findById(savedToken.user?.id!!).orElseThrow {
                UsernameNotFoundException("User not found")
            }
            user.enabled = true
            userRepository.save(user)
            savedToken.validatedAt = LocalDateTime.now()
            tokenRepository.save(savedToken)
        }
    }


    private fun validateEmail(email: String) {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        if (!emailRegex.matches(email)) {
            throw RuntimeException("Invalid email format: $email")
        }
    }

    private fun generateAndSaveActivationToken(user: User): String {
        //Generate a Token
        val generatedToken: String = generateActivationCode(6)
        var token = Token().apply {
            token = generatedToken
            createdAt = LocalDateTime.now()
            expiresAt = LocalDateTime.now().plusMinutes(15)
            this.user = user
            createdBy = user.id
        }
        tokenRepository.save(token)
        return generatedToken
    }

    @Throws(MailSendException::class)
    private fun sendValidationEmail(user: User) {
        val newToken = generateAndSaveActivationToken(user)

        try {

            emailService.sendEmail(
                user.email!!,
                user.fullname(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
            )

        } catch (e: MessagingException) {
            throw MailSendException("Failed to send validation email to ${user.email}", e)
        }
    }

    private fun generateActivationCode(length: Int): String {
        val characters = "0123456789"
        val codeBuilder = StringBuilder()
        val secureRandom = SecureRandom()

        for (i in 0 until length) {
            val randomIndex = secureRandom.nextInt(characters.length)
            codeBuilder.append(characters[randomIndex])
        }

        return codeBuilder.toString()
    }


}