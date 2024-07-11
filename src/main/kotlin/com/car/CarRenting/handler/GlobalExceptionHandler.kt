package com.car.CarRenting.handler

import com.car.CarRenting.dto.response.ExceptionResponse
import com.car.CarRenting.enums.BusinessErrorCodesEnum.*
import jakarta.mail.MessagingException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(LockedException::class)
    fun handleException(exp: LockedException): ResponseEntity<ExceptionResponse> {

        val exceptionResponse = ExceptionResponse()
        exceptionResponse.apply {
            businessErrorCode = ACCOUNT_LOCKED.code
            businessErrorDescription = ACCOUNT_LOCKED.description
            error = exp.message
        }


        return ResponseEntity.status(UNAUTHORIZED).body(exceptionResponse)
    }


    @ExceptionHandler(DisabledException::class)
    fun handleException(exp: DisabledException): ResponseEntity<ExceptionResponse> {

        val exceptionResponse = ExceptionResponse()
        exceptionResponse.apply {
            businessErrorCode = ACCOUNT_DISABLED.code
            businessErrorDescription = ACCOUNT_DISABLED.description
            error = exp.message
        }

        return ResponseEntity.status(UNAUTHORIZED).body(exceptionResponse)
    }


    @ExceptionHandler(BadCredentialsException::class)
    fun handleException(): ResponseEntity<ExceptionResponse> {

        val exceptionResponse = ExceptionResponse()
        exceptionResponse.apply {
            businessErrorCode = BAD_CREDENTIALS.code
            businessErrorDescription = BAD_CREDENTIALS.description
            error = ("Login and / or Password is incorrect!")
        }

        return ResponseEntity.status(UNAUTHORIZED).body(exceptionResponse)

    }


    @ExceptionHandler(MessagingException::class)
    fun handleException(exp: MessagingException): ResponseEntity<ExceptionResponse> {

        val exceptionResponse = ExceptionResponse()
        exceptionResponse.apply {
            error = exp.message
        }

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exceptionResponse)
    }


    @ExceptionHandler(ActivationTokenException::class)
    fun handleException(exp: ActivationTokenException): ResponseEntity<ExceptionResponse> {

        val exceptionResponse = ExceptionResponse()
        exceptionResponse.apply {
            error = exp.message
        }
        return ResponseEntity.status(BAD_REQUEST).body(exceptionResponse)
    }


    @ExceptionHandler(OperationNotPermittedException::class)
    fun handleException(exp: OperationNotPermittedException): ResponseEntity<ExceptionResponse> {

        val exceptionResponse = ExceptionResponse()
        exceptionResponse.apply {
            error = exp.message
        }

        return ResponseEntity.status(BAD_REQUEST).body(exceptionResponse)
    }


    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleException(exp: MethodArgumentNotValidException): ResponseEntity<ExceptionResponse> {

        val errors = mutableSetOf<String>()
        exp.bindingResult.allErrors.forEach { error ->
            val errorMessage = error.defaultMessage
            errors.add(errorMessage!!)
        }

        val exceptionResponse = ExceptionResponse()
        exceptionResponse.apply {
            validationErrors = errors
        }

        return ResponseEntity.status(FORBIDDEN).body(exceptionResponse)

    }


    @ExceptionHandler(Exception::class)
    fun handleException(exp: Exception): ResponseEntity<ExceptionResponse> {


        val exceptionResponse = ExceptionResponse()
        exceptionResponse.apply {
            businessErrorDescription = "Internal Server Error, please contact admin!"
            error = exp.message
        }

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exceptionResponse)
    }

}
