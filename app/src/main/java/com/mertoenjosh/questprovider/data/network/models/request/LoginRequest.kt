package com.mertoenjosh.questprovider.data.network.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("email")
    private val email: String,
    @SerialName("password")
    private val password: String,
)