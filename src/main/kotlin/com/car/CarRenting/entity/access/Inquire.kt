package com.car.CarRenting.entity.access

import com.car.CarRenting.entity.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "inquires")
class Inquire : BaseEntity(){

    @Column(name = "message")
    var message : String? = null


}