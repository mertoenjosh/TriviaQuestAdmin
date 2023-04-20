package com.mertoenjosh.questprovider.domain.repositories

import androidx.paging.PagingData
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.data.network.models.request.LoginRequest
import com.mertoenjosh.questprovider.data.network.models.response.UserResponse
import com.mertoenjosh.questprovider.domain.models.Question
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun loginUser(loginRequest: LoginRequest): UserResponse
    fun getAllQuestions(): Flow<PagingData<QuestionEntity>>
    fun getQuestionById(id: String): Flow<Question>
}