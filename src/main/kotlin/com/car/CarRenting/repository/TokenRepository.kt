package com.car.CarRenting.repository

import com.car.CarRenting.entity.access.Token
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TokenRepository : JpaRepository<Token, Long> {

    fun findByToken(token: String): Optional<Token>
}