package com.mertoenjosh.questprovider.domain.models

data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String , // test@mnt.dev
    val password: String,
    val confirmPassword: String? = null,
    val role: String? = null, // user
)