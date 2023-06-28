package com.mertoenjosh.questprovider.data.data_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mertoenjosh.questprovider.data.data_source.mappers.toDomain
import com.mertoenjosh.questprovider.data.data_source.mappers.toPayload
import com.mertoenjosh.questprovider.data.database.QpDatabase
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.data.network.apis.QuestionApi
import com.mertoenjosh.questprovider.data.paging.QuestProviderRemoteMediator
import com.mertoenjosh.questprovider.data.util.Constants.QUESTIONS_PER_PAGE
import com.mertoenjosh.questprovider.data.util.Helpers
import com.mertoenjosh.questprovider.domain.models.BaseDomainModel
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

    override suspend fun getQuestionById(id: String): Question =
        if (id != "")
            questionDao.getQuestionById(id).toDomain()
        else Question()

    override suspend fun postNewQuestion(question: Question): BaseDomainModel<Question> {
        val response = questionApi.postQuestion(question.toPayload())
        return if (response.isSuccessful) {
            BaseDomainModel(
                error = false,
                message = response.body()?.message ?: "",
                data = response.body()?.data?.toDomain(),
            )
        } else {
            val errRes = Helpers.getErrorResponse(response.errorBody())

            BaseDomainModel(
                error = true,
                message = errRes.message,
                data = null,
            )
        }
    }
}