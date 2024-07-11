package com.car.CarRenting.repository

import com.car.CarRenting.entity.Feedback
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FeedbackRepository: JpaRepository<Feedback, Long> {

    @Query("""
        SELECT feedback
        FROM Feedback feedback
        WHERE feedback.car.id = :carId
    """

    )
    fun findAllByCarId(@Param("carId")carId: Long, pageable: Pageable): Page<Feedback>



}