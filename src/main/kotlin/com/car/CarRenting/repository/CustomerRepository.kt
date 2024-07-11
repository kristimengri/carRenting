package com.car.CarRenting.repository

import com.car.CarRenting.entity.account.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {
}