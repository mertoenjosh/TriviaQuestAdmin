package com.mertoenjosh.questprovider.data.network.models.payload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Login(
    @SerialName("email")
    private val email: String,
    @SerialName("password")
    private val password: String,
)