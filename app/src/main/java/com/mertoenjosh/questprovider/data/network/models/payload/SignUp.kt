package com.mertoenjosh.questprovider.data.network.models.payload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUp(
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("confirmPassword")
    val confirmPassword: String
)