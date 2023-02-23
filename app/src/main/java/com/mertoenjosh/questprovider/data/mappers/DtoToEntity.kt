package com.mertoenjosh.questprovider.data.mappers

import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.data.database.models.UserEntity
import com.mertoenjosh.questprovider.data.network.models.response.QuestionDTO
import com.mertoenjosh.questprovider.data.network.models.response.UserDTO

fun QuestionDTO.toEntity() = QuestionEntity(
    id = id,
    question = question,
    correctAnswer = correctAnswer,
    choices = choices,
    category = category,
    tags = tags,
    difficulty = difficulty,
    author = author
)

fun UserDTO.toEntity() = UserEntity(
    id = id,
    email = email,
    name = name,
    role = role,
    token = "null"
)