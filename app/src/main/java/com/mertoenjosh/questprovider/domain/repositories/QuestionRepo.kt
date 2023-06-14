package com.mertoenjosh.questprovider.domain.repositories

import androidx.paging.PagingData
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.domain.models.Question
import kotlinx.coroutines.flow.Flow

interface QuestionRepo {
    fun getAllQuestions(): Flow<PagingData<QuestionEntity>>
    suspend fun getQuestionById(id: String): Question
}