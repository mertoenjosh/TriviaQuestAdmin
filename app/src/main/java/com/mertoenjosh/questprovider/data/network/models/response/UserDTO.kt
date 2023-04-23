package com.mertoenjosh.questprovider.data.network.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class UserDTO(
    @SerialName("email")
    val email: String, // test@mnt.dev
    @SerialName("_id")
    val id: String, // 637c82a52064d5546ed1ee2e
    @SerialName("name")
    val name: String, // test
    @SerialName("role")
    val role: String = "user", // user
    @SerialName("token")
    val token: String,
)


