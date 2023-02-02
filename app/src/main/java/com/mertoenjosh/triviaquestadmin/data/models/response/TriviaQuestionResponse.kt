package com.mertoenjosh.triviaquestadmin.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TriviaQuestionResponse(
    val status: String,
    val results: Int,
    val requestedAt: String,
    val data: Data
)

@Serializable
data class Data(
    @SerialName("questions")
    val questions: List<QuestionDTO>
)

@Serializable
data class QuestionDTO(
    @SerialName("_id")
    val id: String,
    val question: String,
    val correctAnswer: String,
    val choices: List<String>,
    val difficulty: String = "Easy",
    val category: String,
    val tags: List<String>?,
    val author: String
)