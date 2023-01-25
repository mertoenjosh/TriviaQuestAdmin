package com.mertoenjosh.triviaquestadmin.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TriviaQuestionResponse(
    val status: String,
    val results: Int,
    val requestedAt: String,
    val data: Data
)

@Serializable
data class Data(
    @SerialName("questions")
    val questions: List<QuestionDTO>
)

@Serializable
data class QuestionDTO(
    @SerialName("_id")
    val id: String,
    val question: String,
    val correctAnswer: String,
    val choices: List<String>,
    val difficulty: String = "Easy",
    val category: String,
    val tags: List<String>?,
    val author: String
)


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

/*
    {
        "category": "Science",
        "id": "622a1c377cc59eab6f9504fa",
        "correctAnswer": "methods",
        "incorrectAnswers": [
            "dreams",
            "the therapeutic use of plants",
            "a branch of theology concerned with the final events in the history of the world or of mankind"
        ],
        "question": "What is Methodology the study of?",
        "tags": [
            "science"
        ],
        "type": "Multiple Choice",
        "difficulty": "easy",
        "regions": [],
        "isNiche": false
    },
 */