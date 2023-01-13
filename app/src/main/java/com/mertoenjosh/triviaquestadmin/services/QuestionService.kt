package com.mertoenjosh.triviaquestadmin.services

import com.mertoenjosh.triviaquestadmin.domain.models.QuestionModel
import retrofit2.Call
import retrofit2.http.GET

interface QuestionService {
    @GET("questions")
    fun getQuestions(): Call<List<QuestionModel>>
}