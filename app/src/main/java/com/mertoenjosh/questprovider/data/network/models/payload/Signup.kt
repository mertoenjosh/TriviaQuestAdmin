package com.mertoenjosh.questprovider.data.network.models.payload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Signup(
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("passwordConfirm")
    val confirmPassword: String
)