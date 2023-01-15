package com.mertoenjosh.triviaquestadmin.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Question(
    val id: String,
    val question: String,
    val incorrectAnswers: List<String>,
    val correctAnswer: String,
    val category: String,
    val tags: List<String>,
    val type: String
): Serializable



data class QuestionModel(
    val id: String,
    val question: String,
    val correctAnswer: String,
    @SerializedName("incorrectAnswers")
    val choices: List<String>,
    val difficulty: String = "Easy",
    val category: String,
    val tags: List<String>?,
    val author: String = "mertoenjosh"
): Serializable

fun QuestionModel.formatCategory(): String = this.category
    .split("_")
    .joinToString(" ") { el -> el[0].uppercase() + el.slice(1 until el.length) }

// [general, category]

/*
{
        "choices": [
          "Bill Gates",
          "Mark Zucherburg",
          "Jeff Bezos"
        ],
        "tags": [
          "general_knowledge"
        ],
        "_id": "6343dbbaad6ccece95fedc30",
        "category": "general_knowledge",
        "question": "Who is the richest man on earth (2022)?",
        "correctAnswer": "Elon Musk",
        "difficulty": "easy",
        "author": "mertoenjosh"
      },
 */