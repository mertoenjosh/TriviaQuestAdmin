package com.mertoenjosh.questprovider.data.data_source.mappers

import com.mertoenjosh.questprovider.data.database.models.UserEntity
import com.mertoenjosh.questprovider.data.network.models.payload.Login
import com.mertoenjosh.questprovider.data.network.models.payload.Signup
import com.mertoenjosh.questprovider.data.network.models.response.UserDTO
import com.mertoenjosh.questprovider.data.util.Constants.USER_ID
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
    id = USER_ID,
    email = email,
    firstName = name.split(" ")[0],
    lastName = if (name.split(" ").size > 1) name.split(" ")[1] else "",
    role = role,
    token = token
)

fun UserDTO.toDomain(): User = User(
    firstName = name.split(" ")[0],
    lastName = if (name.split(" ").size < 2) name.split(" ")[0] else "",
    email = email,
    role = role,
    password = ""
)

fun UserEntity.toDomain(): User = User(
    firstName = firstName,
    lastName = lastName,
    email = email,
    role = role,
    password = ""
)