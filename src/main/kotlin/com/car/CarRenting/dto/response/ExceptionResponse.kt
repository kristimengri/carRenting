package com.car.CarRenting.dto.response

import com.fasterxml.jackson.annotation.JsonInclude


@JsonInclude(JsonInclude.Include.NON_EMPTY)
class ExceptionResponse {

    var businessErrorCode: Int? = null
    var businessErrorDescription: String? = null
    var error: String ? = null
    var validationErrors: Set<String> ? = null
    var errors: Map<String, String> ? = null
}