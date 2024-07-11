package com.car.CarRenting.entity

import com.car.CarRenting.entity.car.Car
import com.car.CarRenting.entity.common.BaseEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "feedbacks")
@EntityListeners(AuditingEntityListener::class)
class Feedback : BaseEntity() {

    var note: Double? = null
    var comment: String? = null

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference
    var car: Car? = null
}