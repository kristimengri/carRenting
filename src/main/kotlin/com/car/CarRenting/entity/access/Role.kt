package com.car.CarRenting.entity.access

import com.car.CarRenting.entity.account.User
import com.car.CarRenting.entity.common.BaseEntity
import com.car.CarRenting.enums.RoleEnum
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@Table(name = "roles")
@EntityListeners(AuditingEntityListener::class)
class Role : BaseEntity() {


    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    var name: RoleEnum? = null


    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    var users: MutableList<User> = mutableListOf()


}