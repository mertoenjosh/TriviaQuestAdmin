package com.mertoenjosh.questprovider.data.network.apis

import com.mertoenjosh.questprovider.data.network.models.response.BaseResponse
import com.mertoenjosh.questprovider.data.network.models.response.QuestionDTO
import com.mertoenjosh.questprovider.data.util.Constants.QUESTIONS
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionApi {
    @GET(QUESTIONS)
    suspend fun getAllQuestions(
        @Query("page") page: Int,
        @Query("limit") perPage: Int
    ): BaseResponse<List<QuestionDTO>>
}