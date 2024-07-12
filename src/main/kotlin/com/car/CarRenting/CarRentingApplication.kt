package com.car.CarRenting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
@EnableWebMvc
class CarRentingApplication {

    @GetMapping("/testMessage")
    fun testMessage(): String{
        return "Testing Deploy"
    }


    fun main(args: Array<String>) {
        runApplication<CarRentingApplication>(*args)
    }


}




