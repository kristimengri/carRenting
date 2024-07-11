package com.car.CarRenting.controllers

import com.car.CarRenting.dto.request.CarRequest
import com.car.CarRenting.services.CarService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cars")
@Tag(name = "Cars")
class CarController(
    val carService: CarService
) {

    @PostMapping
    fun saveCar(@RequestBody carRequest: CarRequest, authentication: Authentication): ResponseEntity<Long?> {
        val carId = carService.saveCar(carRequest, authentication)
        return ResponseEntity.status(HttpStatus.CREATED).body(carId)
    }
}