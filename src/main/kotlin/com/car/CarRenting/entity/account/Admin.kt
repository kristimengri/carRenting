package com.car.CarRenting.entity.account

import com.car.CarRenting.entity.common.UserBaseEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "admins")
class Admin : UserBaseEntity() {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "Id")
    @JsonBackReference
    var user: User? = null

}