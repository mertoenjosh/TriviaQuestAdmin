package com.mertoenjosh.questprovider.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mertoenjosh.questprovider.data.database.QpDatabase
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.data.database.models.UserEntity
import com.mertoenjosh.questprovider.data.network.apis.AuthApi
import com.mertoenjosh.questprovider.data.network.apis.QuestionApi
import com.mertoenjosh.questprovider.data.network.models.request.LoginRequest
import com.mertoenjosh.questprovider.data.paging.QuestProviderRemoteMediator
import com.mertoenjosh.questprovider.data.repositories.mappers.toDomain
import com.mertoenjosh.questprovider.data.repositories.mappers.toEntity
import com.mertoenjosh.questprovider.domain.models.BaseDomainModel
import com.mertoenjosh.questprovider.domain.models.Question
import com.mertoenjosh.questprovider.domain.models.User
import com.mertoenjosh.questprovider.domain.repositories.Repository
import com.mertoenjosh.questprovider.util.Constants.QUESTIONS_PER_PAGE
import com.mertoenjosh.questprovider.util.Helpers.getErrorResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val questionApi: QuestionApi,
    private val authApi: AuthApi,
    private val qpDatabase: QpDatabase,
): Repository {
    private val authDao = qpDatabase.authDao()
    private val questionDao = qpDatabase.questionDao()

    override suspend fun loginUser(loginRequest: LoginRequest): BaseDomainModel<User> {
        val response = authApi.login(loginRequest)

        return if (response.isSuccessful) {
            val user = response.body()!!
            cacheUser(user.data.toEntity())

            BaseDomainModel(
                error = false,
                message = "",
                data = user.data.toDomain()
            )
        } else {
            val errorResponse = getErrorResponse(response.errorBody())

            BaseDomainModel(
                error = true,
                message = errorResponse.message,
                data = null
            )
        }
    }

    override suspend fun cacheUser(userEntity: UserEntity) {
        authDao.insertUser(userEntity)
    }

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