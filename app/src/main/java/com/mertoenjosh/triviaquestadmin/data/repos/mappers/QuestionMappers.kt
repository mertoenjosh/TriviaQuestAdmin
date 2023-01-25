package com.mertoenjosh.triviaquestadmin.data.repos.mappers

import com.mertoenjosh.triviaquestadmin.data.models.TriviaQuestion
import com.mertoenjosh.triviaquestadmin.data.models.response.QuestionDTO

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