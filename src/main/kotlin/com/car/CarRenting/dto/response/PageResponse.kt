package com.car.CarRenting.dto.response

data class PageResponse<T>(

    var content: List<T>? = null,
    var number: Int ?= null,
    var size: Int ?= null,
    var totalElements: Long ?= null,
    var totalPages: Int ?= null,
    var first: Boolean ?= null,
    var last: Boolean ?= null
)
