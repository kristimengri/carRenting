package com.car.CarRenting.entity.common

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    var id: Long = 0

    @CreatedDate
    @Column(name = "createdAt", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "modifiedAt", insertable = false)
    var modifiedAt: LocalDateTime? = null

    @CreatedBy
    @Column(name = "createdBy", nullable = false)
    var createdBy: Long = 0L

    @Column(name = "modifiedBy")
    var modifiedBy: String? = null
}