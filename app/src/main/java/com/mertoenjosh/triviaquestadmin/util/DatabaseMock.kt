package com.mertoenjosh.triviaquestadmin.util

import android.util.Log
import com.mertoenjosh.triviaquestadmin.domain.models.QuestionModel
import com.mertoenjosh.triviaquestadmin.services.QuestionService
import com.mertoenjosh.triviaquestadmin.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


val questionList: List<QuestionModel> = getQuestions()

fun getQuestions(): List<QuestionModel> {
    val service: QuestionService = ServiceBuilder.buildService(QuestionService::class.java)
    val serviceTask = service.getQuestions()
    var res = listOf<QuestionModel>()

    serviceTask.enqueue( object: Callback<List<QuestionModel>> {
        override fun onResponse(
            request: Call<List<QuestionModel>>,
            response: Response<List<QuestionModel>>
        ) {
            Log.d("QUESTIONS...", "onResponse: ${response.body()}")
            res = response.body()!!
        }

        override fun onFailure(response: Call<List<QuestionModel>>, t: Throwable) {
            Log.e("QUESTIONS...", "onFailure: $response", t)
        }

    })
    return res
}

/*
val questions = listOf(
    QuestionModel(
        id = "6343dbbaad6ccece95fedc30",
        question = "Who is the richest man on earth (2022)?",
        choices = listOf("Bill Gates", "Mark Zucherburg", "Jeff Bezos"),
        correctAnswer = "general_knowledge",
        difficulty = "easy",
        category = "general_knowledge",
        tags = listOf("general_knowledge"),
    ),
    QuestionModel(
        id = "6343dbbaad6ccece95fedc30",
        question = "Who is the richest man on earth (2022)?",
        choices = listOf("Bill Gates", "Mark Zucherburg", "Jeff Bezos"),
        correctAnswer = "general_knowledge",
        difficulty = "hard",
        category = "general_knowledge",
        tags = listOf("general_knowledge"),
        author = "mertoenjosh"
    ),
    QuestionModel(
        id = "6343dbbaad6ccece95fedc30",
        question = "Who is the richest man on earth (2022)?",
        choices = listOf("Bill Gates", "Mark Zucherburg", "Jeff Bezos"),
        correctAnswer = "general_knowledge",
        difficulty = "easy",
        category = "general_knowledge",
        tags = listOf("general_knowledge"),
        author = "mertoenjosh"
    ),
    QuestionModel(
        id = "6343dbbaad6ccece95fedc30",
        question = "Who is the richest man on earth (2022)?",
        choices = listOf("Bill Gates", "Mark Zucherburg", "Jeff Bezos"),
        correctAnswer = "general_knowledge",
        difficulty = "medium",
        category = "general_knowledge",
        tags = listOf("general_knowledge"),
        author = "mertoenjosh"
    ),
    QuestionModel(
        id = "6343dbbaad6ccece95fedc30",
        question = "Who is the richest man on earth (2022)?",
        choices = listOf("Bill Gates", "Mark Zucherburg", "Jeff Bezos"),
        correctAnswer = "general_knowledge",
        difficulty = "easy",
        category = "general_knowledge",
        tags = listOf("general_knowledge"),
        author = "mertoenjosh"
    ),
    QuestionModel(
        id = "6343dbbaad6ccece95fedc30",
        question = "Who is the richest man on earth (2022)?",
        choices = listOf("Bill Gates", "Mark Zucherburg", "Jeff Bezos"),
        correctAnswer = "general_knowledge",
        difficulty = "medium",
        category = "general_knowledge",
        tags = listOf("general_knowledge"),
        author = "mertoenjosh"
    ),
    QuestionModel(
        id = "6343dbbaad6ccece95fedc30",
        question = "Who is the richest man on earth (2022)?",
        choices = listOf("Bill Gates", "Mark Zucherburg", "Jeff Bezos"),
        correctAnswer = "general_knowledge",
        difficulty = "hard",
        category = "general_knowledge",
        tags = listOf("general_knowledge"),
        author = "mertoenjosh"
    )

)
 */