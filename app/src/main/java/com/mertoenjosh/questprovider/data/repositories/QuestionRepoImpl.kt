package com.mertoenjosh.questprovider.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mertoenjosh.questprovider.data.database.QpDatabase
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.data.network.apis.QuestionApi
import com.mertoenjosh.questprovider.data.paging.QuestProviderRemoteMediator
import com.mertoenjosh.questprovider.data.repositories.mappers.toDomain
import com.mertoenjosh.questprovider.data.util.Constants.QUESTIONS_PER_PAGE
import com.mertoenjosh.questprovider.domain.models.Question
import com.mertoenjosh.questprovider.domain.repositories.QuestionRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuestionRepoImpl @Inject constructor(
    private val questionApi: QuestionApi,
    private val qpDatabase: QpDatabase,
) : QuestionRepo {
    private val questionDao = qpDatabase.questionDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllQuestions(): Flow<PagingData<QuestionEntity>> {
        val pagingSourceFactory = { questionDao.getAllQuestions() }
        return Pager(
            config = PagingConfig(pageSize = QUESTIONS_PER_PAGE),
            remoteMediator = QuestProviderRemoteMediator(
                questionApi = questionApi,
                qpDatabase = qpDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun getQuestionById(id: String): Question {
        return if (id != "")
            questionDao.getQuestionById(id).toDomain()
        else Question()
    }
}