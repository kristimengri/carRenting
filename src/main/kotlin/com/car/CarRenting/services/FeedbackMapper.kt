package com.car.CarRenting.services

import com.car.CarRenting.dto.request.FeedbackRequest
import com.car.CarRenting.dto.response.FeedbackResponse
import com.car.CarRenting.entity.Feedback
import com.car.CarRenting.entity.car.Car
import org.springframework.stereotype.Service
import java.util.*

@Service
class FeedbackMapper {

    fun toFeedback(feedbackRequest : FeedbackRequest) : Feedback{

        var feedbackCar = Car().apply{
            id = feedbackRequest.carId!!
        }

        val feedback = Feedback().apply {
            note = feedbackRequest.note
            comment = feedbackRequest.comment
            car = feedbackCar
        }

        return feedback

    }


    fun toFeedbackResponse(feedback: Feedback, id: Long): FeedbackResponse {
        var feedbackResponse = FeedbackResponse().apply {
            note = feedback.note
            comment = feedback.comment
            ownFeedback = Objects.equals(feedback.createdBy, id)
        }
        return feedbackResponse
    }
}