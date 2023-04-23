package com.mertoenjosh.questprovider.data.repositories.mappers

import com.mertoenjosh.questprovider.data.network.models.response.UserDTO
import com.mertoenjosh.questprovider.domain.models.User

fun UserDTO.toDomain() = User(
    email = email,
    name = name,
    role = role
)