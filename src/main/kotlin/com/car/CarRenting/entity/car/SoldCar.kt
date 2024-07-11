package com.car.CarRenting.entity.car

import com.car.CarRenting.entity.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "soldCars")
class SoldCar: BaseEntity() {

    @Column(name = "sellPrice")
    var sellPrice : Double ? = null

}