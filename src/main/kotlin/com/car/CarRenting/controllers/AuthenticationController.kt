package com.car.CarRenting.controllers

import com.car.CarRenting.dto.request.AuthenticationRequest
import com.car.CarRenting.dto.request.RegistrationRequest
import com.car.CarRenting.dto.response.AuthenticationResponse
import com.car.CarRenting.services.AuthenticationService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.mail.MessagingException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("auth")
@Tag(name = "Authentication")
class AuthenticationController(private val service: AuthenticationService) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Throws(MessagingException::class)
    fun register(
        @RequestBody @Valid request: RegistrationRequest
    ): ResponseEntity<Any> {
        service.register(request)
        return ResponseEntity.accepted().build()

    }


    @PostMapping("/authenticate")
    fun authenticate(
        @RequestBody @Valid authenticationRequest: AuthenticationRequest
    ): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(service.authenticate(authenticationRequest))
    }


    @GetMapping("/activate-account")
    fun confirm(
        @RequestParam token: String
    ) {
        service.activateAccount(token)
    }

}