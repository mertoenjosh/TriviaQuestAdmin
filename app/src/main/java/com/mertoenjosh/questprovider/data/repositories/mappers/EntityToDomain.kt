package com.mertoenjosh.questprovider.data.repositories.mappers

import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.data.database.models.UserEntity
import com.mertoenjosh.questprovider.domain.models.Question
import com.mertoenjosh.questprovider.domain.models.User

fun QuestionEntity.toDomain() = Question (
    id= id,
    question = question,
    correctAnswer = correctAnswer,
    choices = choices,
    difficulty = difficulty,
    category = category,
    tags = tags,
    author = author
)

fun UserEntity.toDomain() = User(
    email = email,
    name = name,
    role = role,
)