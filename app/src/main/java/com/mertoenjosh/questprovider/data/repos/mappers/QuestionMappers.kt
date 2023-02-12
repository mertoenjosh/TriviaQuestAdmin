package com.mertoenjosh.questprovider.data.repos.mappers

import com.mertoenjosh.questprovider.data.models.TriviaQuestion
import com.mertoenjosh.questprovider.data.models.response.QuestionDTO

fun QuestionDTO.toEntity() = TriviaQuestion(
    id = id,
    question = question,
    correctAnswer = correctAnswer,
    choices = choices,
    category = category,
    tags = tags,
    difficulty = difficulty,
    author = author
)