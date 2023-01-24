package com.mertoenjosh.triviaquestadmin.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertoenjosh.triviaquestadmin.util.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.QUESTIONS_TABLE)
data class Question(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val question: String,
    val correctAnswer: String,
    @SerialName("incorrectAnswers")
    val choices: List<String>,
    val difficulty: String = "Easy",
    val category: String,
    val tags: List<String>?,
    val author: String = "mertoenjosh"
)

fun Question.formatCategory(): String = this.category
    .split("_")
    .joinToString(" ") { el -> el[0].uppercase() + el.slice(1 until el.length) }

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