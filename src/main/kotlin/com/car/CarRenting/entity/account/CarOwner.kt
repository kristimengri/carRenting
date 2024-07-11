package com.car.CarRenting.entity.account

import com.car.CarRenting.entity.car.Car
import com.car.CarRenting.entity.common.UserBaseEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "car_owners")
class CarOwner : UserBaseEntity() {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "Id")
    @JsonBackReference
    var user: User? = null

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var cars: MutableList<Car> = mutableListOf()
}