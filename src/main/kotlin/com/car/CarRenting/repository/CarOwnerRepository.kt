package com.car.CarRenting.repository

import com.car.CarRenting.entity.account.CarOwner
import org.springframework.data.jpa.repository.JpaRepository

interface CarOwnerRepository: JpaRepository<CarOwner, Long> {
}