package com.car.CarRenting.dto.request


import com.car.CarRenting.enums.GenderEnum
import com.car.CarRenting.enums.RoleEnum
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

class RegistrationRequest {

    var userName: String? = null
    var gender: GenderEnum? = null
    var address: String? = null
    var city: String? = null
    var country: String? = null
    var phoneNumber: String? = null

    @NotEmpty(message = "Emrin o kr")
    @NotBlank(message = "Emrin o kr")
//  @NotEmptyString(message = "Emrin o kr")
    var firstname: String? = null

    @NotEmpty(message = "Mbiemrin o kr")
    @NotBlank(message = "Mbiemrin o kr")
//  @NotEmptyString(message = "Mbiemrin  o kr")
    var lastname: String?? = null

    @Email(message = "Nuk e ke email to sakt o kr")
//  @EmailVerification(message = "Nuk e ke email to sakt o kr")
    @NotEmpty(message = "Pa email do regjistrohesh o kr")
    @NotBlank(message = "Pa email do regjistrohesh o kr")
//  @NotEmptyString(message = "Pa email do regjistrohesh o kr")
    var email: String? = null

    @NotEmpty(message = "Passwordin se na cave kr")
    @NotBlank(message = "Passwordin se na cave kr")
    @Size(min = 4, message = "Passwordi t pakten 4 germa se i qr")
//  @NotEmptyString(message = "Pa pasurod do regjistrohesh o kr")
    var password: String? = null

    //    @NotEmpty(message = "Ca do me u bo o kr")
//    @NotBlank(message = "Ca do me u bo o kr")
    val role: RoleEnum? = null


}