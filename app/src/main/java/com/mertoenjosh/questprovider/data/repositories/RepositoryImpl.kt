package com.mertoenjosh.questprovider.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mertoenjosh.questprovider.data.database.QpDatabase
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.data.repositories.mappers.toEntity
import com.mertoenjosh.questprovider.data.network.apis.AuthApi
import com.mertoenjosh.questprovider.data.network.apis.QuestionApi
import com.mertoenjosh.questprovider.data.network.models.request.LoginRequest
import com.mertoenjosh.questprovider.data.network.models.response.UserResponse
import com.mertoenjosh.questprovider.data.paging.QuestProviderRemoteMediator
import com.mertoenjosh.questprovider.util.Constants.QUESTIONS_PER_PAGE
import com.mertoenjosh.questprovider.util.Utils.getErrorResponse
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val questionApi: QuestionApi,
    private val authApi: AuthApi,
    private val qpDatabase: QpDatabase,
) {
    private val userDao = qpDatabase.userDao()
    private val questionDao = qpDatabase.questionDao()

    suspend fun loginUser(loginRequest: LoginRequest): UserResponse {
        val user: UserResponse
        val response = authApi.login(loginRequest)

        if (response.isSuccessful) {
            Timber.i("RESPONSE SUCCESS-------> %s", response.body())
            user = response.body()!!
            userDao.insertUser(user.data?.user?.toEntity()!!)
        } else {
            val errorResponse = getErrorResponse(response.errorBody())
            Timber.e("RESPONSE FAIL ---------> %s", errorResponse.message)

            user = UserResponse(
                status = errorResponse.status,
                message = errorResponse.message,
                token = null,
                data = null
            )
        }

        return user
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getAllQuestions(): Flow<PagingData<QuestionEntity>> {
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
}