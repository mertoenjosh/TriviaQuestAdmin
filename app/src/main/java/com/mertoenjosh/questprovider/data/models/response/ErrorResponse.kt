package com.mertoenjosh.questprovider.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: String
)