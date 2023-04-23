package com.mertoenjosh.questprovider.domain.repositories

import androidx.paging.PagingData
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.data.database.models.UserEntity
import com.mertoenjosh.questprovider.data.network.models.request.LoginRequest
import com.mertoenjosh.questprovider.domain.models.BaseDomainModel
import com.mertoenjosh.questprovider.domain.models.Question
import com.mertoenjosh.questprovider.domain.models.User
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun loginUser(loginRequest: LoginRequest): BaseDomainModel<User>
    suspend fun cacheUser(userEntity: UserEntity)
    fun getAllQuestions(): Flow<PagingData<QuestionEntity>>
    suspend fun getQuestionById(id: String): Question
}