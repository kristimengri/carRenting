package com.car.CarRenting.entity.car

import com.car.CarRenting.entity.common.BaseEntity
import com.car.CarRenting.enums.DurationUnitEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "rentedCars")
class RentedCar : BaseEntity() {

    @Column(name = "rentPrice")
    var rentPrice: Double? = null

    @Column(name = "durationValue")
    var durationValue: Double? = null

    @Column(name = "durationUnit")
    var durationUnit: DurationUnitEnum? = null

    @Column(name = "isAvailable")
    var isAvailable : Boolean = false
}