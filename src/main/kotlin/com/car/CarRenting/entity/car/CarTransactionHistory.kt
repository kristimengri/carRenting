package com.car.CarRenting.entity.car

import com.car.CarRenting.entity.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "car_transaction_histories")
class CarTransactionHistory : BaseEntity() {

    //userRelationship
    //carRelationship


    var returned: Boolean = false
    var returnedApproved: Boolean = false
}