package com.car.CarRenting.entity.access

import com.car.CarRenting.entity.common.BaseEntity
import com.car.CarRenting.enums.BookingStatusEnum
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "bookings")
class Booking : BaseEntity(){

    @Column(name = "startDate")
    var startDate: LocalDateTime? = null

    @Column(name = "endDate")
    var endDate: LocalDateTime? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var bookingStatus: BookingStatusEnum? = null

}