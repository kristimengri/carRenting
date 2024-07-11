package com.car.CarRenting.enums

import org.springframework.http.HttpStatus

enum class BusinessErrorCodesEnum(

    val code: Int,
    val description: String,
    val httpStatus: HttpStatus

) {


    NO_CODE(0, "No code", HttpStatus.NOT_IMPLEMENTED),
    INCORRECT_CURRENT_PASSWORD(300, "Current password is incorrect", HttpStatus.BAD_REQUEST),
    NEW_PASSWORD_DOES_NOT_MATCH(301, "The new password does not match", HttpStatus.BAD_REQUEST),
    ACCOUNT_LOCKED(302, "User account is locked", HttpStatus.FORBIDDEN),
    ACCOUNT_DISABLED(303, "User account is disabled", HttpStatus.FORBIDDEN),
    BAD_CREDENTIALS(304, "Login and / or Password is incorrect", HttpStatus.FORBIDDEN)

}