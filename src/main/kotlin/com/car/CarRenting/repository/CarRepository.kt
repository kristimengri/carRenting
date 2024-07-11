package com.car.CarRenting.repository

import com.car.CarRenting.entity.car.Car
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository : JpaRepository<Car, Long> {
}