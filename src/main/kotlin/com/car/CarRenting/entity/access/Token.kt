package com.car.CarRenting.entity.access

import com.car.CarRenting.entity.account.User
import com.car.CarRenting.entity.common.BaseEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "tokens")
class Token : BaseEntity() {


    var token: String? = null
    var expiresAt: LocalDateTime? = null
    var validatedAt: LocalDateTime? = null

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference
    var user: User? = null
}