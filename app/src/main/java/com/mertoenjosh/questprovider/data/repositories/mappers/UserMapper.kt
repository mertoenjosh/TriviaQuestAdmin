package com.mertoenjosh.questprovider.data.repositories.mappers

import com.mertoenjosh.questprovider.data.database.models.UserEntity
import com.mertoenjosh.questprovider.data.network.models.payload.Login
import com.mertoenjosh.questprovider.data.network.models.payload.Signup
import com.mertoenjosh.questprovider.data.network.models.response.UserDTO
import com.mertoenjosh.questprovider.domain.models.User

fun User.toLoginPayload(): Login = Login(
    email = email,
    password = password
)

fun User.toSignupPayload(): Signup = Signup(
    name = "$firstName $lastName",
    email = email,
    password = password,
    confirmPassword = confirmPassword!!
)

fun UserDTO.toEntity(): UserEntity = UserEntity(
    id = id,
    email = email,
    name = name,
    role = role,
    token = token
)

fun UserDTO.toUser(): User = User(
    firstName = name.split(" ")[0],
    lastName = name.split(" ")[1],
    email = email,
    role = role,
    password = ""
)