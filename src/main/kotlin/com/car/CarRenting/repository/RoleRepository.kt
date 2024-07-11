package com.car.CarRenting.repository

import com.car.CarRenting.entity.access.Role
import com.car.CarRenting.enums.RoleEnum
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(role: RoleEnum): Optional<Role>
}