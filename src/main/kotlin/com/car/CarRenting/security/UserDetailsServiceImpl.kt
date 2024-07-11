package com.car.CarRenting.security

import com.car.CarRenting.repository.UserRepository
import com.car.CarRenting.services.AuthenticationService
import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@RequiredArgsConstructor
class UserDetailsServiceImpl(
    private val userRepository: UserRepository,
    private val logger: Logger = LoggerFactory.getLogger(AuthenticationService::class.java)
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(userEmail: String?): UserDetails {
        logger.debug("Attempting to load user by email: {}", userEmail)
        val user = userRepository.findByEmail(userEmail!!)
            .orElseThrow {
                UsernameNotFoundException("User not found for email: $userEmail")
            }
        logger.debug("Successfully loaded user by email: {}", userEmail)
        return user
    }

}