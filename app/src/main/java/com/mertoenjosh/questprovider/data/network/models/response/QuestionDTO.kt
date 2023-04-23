package com.mertoenjosh.questprovider.data.network.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class QuestionDTO(
    @SerialName("_id")
    val id: String,
    val question: String,
    val correctAnswer: String,
    val choices: List<String>,
    val difficulty: String,
    val category: String,
    val tags: List<String>? = listOf(),
    val author: String
)