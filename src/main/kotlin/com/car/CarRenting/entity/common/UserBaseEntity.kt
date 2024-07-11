package com.car.CarRenting.entity.common

import com.car.CarRenting.enums.GenderEnum
import com.car.CarRenting.enums.RoleEnum
import com.car.CarRenting.enums.StatusEnum
import jakarta.persistence.Column
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
open class UserBaseEntity : BaseEntity() {

    @Column(name = "username", nullable = false)
    var userName: String? = null

    @Column(name = "password", nullable = false)
    @JvmField
    var password: String? = null

    @Column(name = "email", nullable = false)
    var email: String? = null

    @Column(name = "firstname", nullable = false)
    var firstname: String? = null

    @Column(name = "lastname", nullable = false)
    var lastname: String? = null

    @Column(name = "role")
    var role: RoleEnum? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    var gender: GenderEnum = GenderEnum.MALE

    @Column(name = "address")
    var address: String? = null

    @Column(name = "city", nullable = false)
    var city: String? = null

    @Column(name = "country", nullable = false)
    var country: String? = null

    @Column(name = "phoneNumber", nullable = false)
    var phoneNumber: String? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: StatusEnum = StatusEnum.ACTIVE

    @Column(name = "enabled")
    var enabled: Boolean = false

    @Column(name = "accountLocked")
    var accountLocked: Boolean = false

    @Column(name = "isDeleted")
    var isDeleted: Boolean = false


    fun copyFrom(other: UserBaseEntity) {
        this.userName = other.userName
        this.password = other.password
        this.email = other.email
        this.firstname = other.firstname
        this.lastname = other.lastname
        this.role = other.role
        this.gender = other.gender
        this.address = other.address
        this.city = other.city
        this.country = other.country
        this.phoneNumber = other.phoneNumber
        this.status = other.status
        this.enabled = other.enabled
        this.accountLocked = other.accountLocked
        this.isDeleted = other.isDeleted
    }



}