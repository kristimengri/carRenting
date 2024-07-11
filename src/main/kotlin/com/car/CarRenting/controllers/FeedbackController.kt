package com.car.CarRenting.controllers

import com.car.CarRenting.dto.request.FeedbackRequest
import com.car.CarRenting.dto.response.FeedbackResponse
import com.car.CarRenting.dto.response.PageResponse
import com.car.CarRenting.services.FeedbackService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/feedbacks")
@Tag(name = "Feedback")
class FeedbackController(
        val feedbackService: FeedbackService
) {

    @PostMapping
    fun saveFeedback(
        @Valid @RequestBody feedbackRequest: FeedbackRequest,
        authentication: Authentication
    ): ResponseEntity<Long> {

        return ResponseEntity.ok(feedbackService.save(feedbackRequest, authentication))
    }

    @GetMapping("/car/{car-id}")
    fun findAllFeedbackByCar(
        @PathVariable("car-id") carId : Long,
        @RequestParam(name = "page", defaultValue = "0", required = false) page: Int,
        @RequestParam(name = "size", defaultValue = "10", required = false) size: Int,
        authentication: Authentication
        ) : ResponseEntity<PageResponse<FeedbackResponse>>{


        return ResponseEntity.ok(feedbackService.findAllFeedbacksByCar(carId, page, size,authentication))

    }

}