package com.car.CarRenting.entity.car

import com.car.CarRenting.entity.Feedback
import com.car.CarRenting.entity.account.CarOwner
import com.car.CarRenting.entity.account.User
import com.car.CarRenting.entity.common.BaseEntity
import com.car.CarRenting.enums.DoorsEnum
import com.car.CarRenting.enums.FuelTypeEnum
import com.car.CarRenting.enums.TransmissionTypeEnum
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "cars")
class Car : BaseEntity() {

    @Column(name = "model")
    var model: String? = null

    @Column(name = "rentPrice")
    var rentPrice: Double? = null

    @Column(name = "sellPrice")
    var sellPrice: Double? = null

    @Column(name = "fuleType")
    var fuelType: FuelTypeEnum? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "transmissionType")
    var transmissionType: TransmissionTypeEnum? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "doorType")
    var doorType: DoorsEnum = DoorsEnum.FOUR_DOORS_MORE_WHORES

    @Column(name = "color")
    var color: String? = null

    @Column(name = "description")
    var description: String? = null

    @Column(name = "isAvailable")
    var isAvailable: Boolean = false

//    @Lob
//    @Column(name = "imageData", columnDefinition = "bytea")
//    var imageData: ByteArray? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_owner_id")
    @JsonBackReference
    var owner: CarOwner? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    var user: User? = null

    @OneToMany(mappedBy = "car")
    @JsonManagedReference
    var feedbacks: List<Feedback>? = null
}