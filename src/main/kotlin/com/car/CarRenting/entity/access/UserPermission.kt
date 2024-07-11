package com.car.CarRenting.entity.access

import com.car.CarRenting.entity.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "userPermissions")
class UserPermission : BaseEntity() {

    var userId: String? = null
}