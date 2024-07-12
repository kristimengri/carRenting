package com.car.CarRenting.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestDeploy {

    @GetMapping("/testDeploy")
    fun testDeploy(): String{
        return "Testing Deploy"
    }
}