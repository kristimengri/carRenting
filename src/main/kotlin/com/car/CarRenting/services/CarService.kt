package com.car.CarRenting.services

import com.car.CarRenting.dto.request.CarRequest
import com.car.CarRenting.entity.account.CarOwner
import com.car.CarRenting.entity.car.Car
import com.car.CarRenting.enums.RoleEnum
import com.car.CarRenting.repository.CarOwnerRepository
import com.car.CarRenting.repository.CarRepository
import com.car.CarRenting.repository.UserRepository
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CarService(
    val carRepository: CarRepository,
    val userRepository: UserRepository,
    val carOwnerRepository: CarOwnerRepository,
) {

    fun saveCar(carRequest: CarRequest, authentication: Authentication): Long? {


        val userDetails = authentication.principal as UserDetails

        val user = userRepository.findByEmail(userDetails.username).orElseThrow {
            RuntimeException("User not found")
        }

        if (user.roles.none { it.name == RoleEnum.CAR_OWNER }) {
            throw RuntimeException("User does not have the CAR_OWNER role")
        }

        val carOwner = user.carOwner ?: CarOwner().apply {
            this.user = user
            user.carOwner = this
            carOwnerRepository.save(this)
        }


        val car = Car().apply {
            model = carRequest.model
            rentPrice = carRequest.rentPrice
            sellPrice = carRequest.sellPrice
            fuelType = carRequest.fuelType
            transmissionType = carRequest.transmissionType
            doorType = carRequest.doorType!!
            color = carRequest.color
            description = carRequest.description
            isAvailable = true
//            imageData = null
            owner = carOwner
        }

        val savedCar = carRepository.save(car)

        return savedCar.id

    }
}