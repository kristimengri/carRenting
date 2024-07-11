package com.car.CarRenting.services

import com.car.CarRenting.dto.request.FeedbackRequest
import com.car.CarRenting.dto.response.FeedbackResponse
import com.car.CarRenting.dto.response.PageResponse
import com.car.CarRenting.entity.Feedback
import com.car.CarRenting.entity.account.User
import com.car.CarRenting.entity.car.Car
import com.car.CarRenting.repository.CarRepository
import com.car.CarRenting.repository.FeedbackRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class FeedbackService(
    val carRepository: CarRepository,
    val feedbackRepository: FeedbackRepository,
    val feedbackMapper: FeedbackMapper
) {

    fun save(feedbackRequest: FeedbackRequest, authentication: Authentication): Long? {

        val car : Car = carRepository.findById(feedbackRequest.carId!!).orElseThrow {
            EntityNotFoundException("This specific car was not found!")
        }

        //Check if car is allowed to get a feedback

        val feedback : Feedback = feedbackMapper.toFeedback(feedbackRequest)

        return feedbackRepository.save(feedback).id
    }

    @Transactional
    fun findAllFeedbacksByCar(
         carId: Long,
         page: Int,
         pageSize: Int,
         authentication: Authentication
    ) : PageResponse<FeedbackResponse> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        val user: User = authentication.principal as User
        val feedbacks : Page<Feedback> = feedbackRepository.findAllByCarId(carId, pageable)

        val feedbackResponses : List<FeedbackResponse> = feedbacks.content.map { feedbackMapper.toFeedbackResponse(it, user.id) }


        return PageResponse(
            content = feedbackResponses,
            number = feedbacks.number,
            size = feedbacks.size,
            totalElements = feedbacks.totalElements,
            totalPages = feedbacks.totalPages,
            first = feedbacks.isFirst,
            last = feedbacks.isLast
        )
    }


}