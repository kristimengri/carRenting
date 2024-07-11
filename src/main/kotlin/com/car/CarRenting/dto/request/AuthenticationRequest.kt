package com.car.CarRenting.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

class AuthenticationRequest {

    @Email(message = "Nuk e ke email to sakt o kr")
    @NotEmpty(message = "Pa email do regjistrohesh o kr")
    @NotBlank(message = "Pa email do regjistrohesh o kr")
    var email: String? = null

    @NotEmpty(message = "Passwordin se na cave kr")
    @NotBlank(message = "Passwordin se na cave kr")
    @Size(min = 4, message = "Passwordi t pakten 4 germa se i qr")
    var password: String? = null

}