package com.mertoenjosh.questprovider.data.repositories.mappers

import com.mertoenjosh.questprovider.data.database.models.UserEntity
import com.mertoenjosh.questprovider.data.network.models.payload.Login
import com.mertoenjosh.questprovider.data.network.models.response.UserDTO
import com.mertoenjosh.questprovider.domain.models.User

fun User.toPayload(): Login = Login(
    email = email,
    password = password
)

fun UserDTO.toEntity(): UserEntity = UserEntity(
    id = id,
    email = email,
    name = name,
    role = role,
    token = token
)

fun UserDTO.toUser(): User = User(
    firstName = name,
    lastName = name,
    email = email,
    role = role,
    password = ""
)