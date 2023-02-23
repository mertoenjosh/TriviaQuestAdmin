package com.mertoenjosh.questprovider.data.network.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("status")
    val status: String, // success
    @SerialName("token")
    val token: String?, // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzN2M4MmE1MjA2NGQ1NTQ2ZWQxZWUyZSIsImlhdCI6MTY3NTkxMzEwMiwiZXhwIjoxNjgzNjg5MTAyfQ.N9bbw4Z_QC334o706tuMBf8QJtb8KahsMy0XJiLnHas
    @SerialName("data")
    val `data`: Data?,
    @SerialName("message")
    val message: String? = null
) {
    @Serializable
    data class Data(
        @SerialName("user")
        val user: UserDTO
    )
}
@Serializable
data class UserDTO(
    @SerialName("email")
    val email: String, // test@mnt.dev
    @SerialName("_id")
    val id: String, // 637c82a52064d5546ed1ee2e
    @SerialName("name")
    val name: String, // test
    @SerialName("role")
    val role: String, // user
)


