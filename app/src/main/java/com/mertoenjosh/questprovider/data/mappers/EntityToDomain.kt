package com.mertoenjosh.questprovider.data.mappers

import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.domain.models.Question

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