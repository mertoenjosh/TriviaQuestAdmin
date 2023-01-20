package com.mertoenjosh.triviaquestadmin.data.network.apis

import com.mertoenjosh.triviaquestadmin.domain.models.QuestionModel
import retrofit2.Call
import retrofit2.http.GET

interface QuestionApi {
    @GET("questions")
    suspend fun getQuestions(): Call<List<QuestionModel>>
}