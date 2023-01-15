package com.mertoenjosh.triviaquestadmin.util

import com.mertoenjosh.triviaquestadmin.domain.models.QuestionModel
import com.mertoenjosh.triviaquestadmin.services.QuestionService
import com.mertoenjosh.triviaquestadmin.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

//fun getQuestions(): List<QuestionModel> {
//    val service: QuestionService = ServiceBuilder.buildService(QuestionService::class.java)
//    val serviceTask = service.getQuestions()
//    var res = listOf<QuestionModel>()
//
//    serviceTask.enqueue( object: Callback<List<QuestionModel>> {
//        override fun onResponse(
//            request: Call<List<QuestionModel>>,
//            response: Response<List<QuestionModel>>
//        ) {
//            Timber.tag("QUESTIONS...").d("onResponse: %s", response.body())
//            res = response.body()!!
//        }
//
//        override fun onFailure(response: Call<List<QuestionModel>>, t: Throwable) {
//            Timber.tag("QUESTIONS...").e(t, "onFailure: %s", response)
//        }
//
//    })
//    return res
//}

val mockQuestions = listOf(
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
