package com.mertoenjosh.questprovider.data.network.apis

import com.mertoenjosh.questprovider.data.models.response.TriviaQuestionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionApi {
    @GET("questions")
    suspend fun getAllQuestions(
        @Query("page") page: Int,
        @Query("limit") perPage: Int
    ): TriviaQuestionResponse
}