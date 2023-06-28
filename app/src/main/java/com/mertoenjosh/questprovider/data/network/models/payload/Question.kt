package com.mertoenjosh.questprovider.data.network.models.payload


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    @SerialName("choices")
    val choices: List<String>,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("category")
    val category: String,
    @SerialName("question")
    val question: String,
    @SerialName("correctAnswer")
    val correctAnswer: String,
    @SerialName("difficulty")
    val difficulty: String,
    @SerialName("author")
    val author: String
)