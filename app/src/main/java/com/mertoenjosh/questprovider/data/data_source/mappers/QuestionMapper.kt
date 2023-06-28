package com.mertoenjosh.questprovider.data.data_source.mappers

import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.data.network.models.response.QuestionDTO
import com.mertoenjosh.questprovider.domain.models.Question

fun QuestionEntity.toDomain() = Question(
    id = id,
    question = question,
    correctAnswer = correctAnswer,
    choices = choices,
    difficulty = difficulty,
    category = category,
    tags = tags,
    author = author
)

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

fun Question.toPayload() = com.mertoenjosh.questprovider.data.network.models.payload.Question(
    question = question,
    correctAnswer = correctAnswer,
    choices = choices,
    difficulty = difficulty,
    category = category,
    tags = tags ?: listOf(),
    author = author
)

fun QuestionDTO.toDomain() = Question(
    id = id,
    question = question,
    correctAnswer = correctAnswer,
    choices = choices,
    category = category,
    tags = tags,
    difficulty = difficulty,
    author = author
)