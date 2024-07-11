package com.car.CarRenting.repository

import com.car.CarRenting.entity.account.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
}