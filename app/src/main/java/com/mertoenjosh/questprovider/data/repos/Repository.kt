package com.mertoenjosh.questprovider.data.repos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mertoenjosh.questprovider.data.database.QuestionsDatabase
import com.mertoenjosh.questprovider.data.models.TriviaQuestion
import com.mertoenjosh.questprovider.data.models.request.LoginRequest
import com.mertoenjosh.questprovider.data.models.response.UserResponse
import com.mertoenjosh.questprovider.data.network.apis.AuthApi
import com.mertoenjosh.questprovider.data.network.apis.QuestionApi
import com.mertoenjosh.questprovider.data.paging.QuestProviderRemoteMediator
import com.mertoenjosh.questprovider.util.Constants.QUESTIONS_PER_PAGE
import com.mertoenjosh.questprovider.util.Utils.getErrorBody
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class Repository @Inject constructor(
    private val questionApi: QuestionApi,
    private val authApi: AuthApi,
    private val questProviderDatabase: QuestionsDatabase
) {
    suspend fun loginUser(loginRequest: LoginRequest): UserResponse {
        val user: UserResponse
        val response = authApi.login(loginRequest)
        if (response.isSuccessful) {
            Timber.d("RESPONSE SUCCESS-------> %s", response.body())

            user = response.body()!!
        }else {
            val errorRes = getErrorBody(response.errorBody())

            Timber.d("RESPONSE FAIL ---------> %s", errorRes.message)

            user = UserResponse(
                status = errorRes.status,
                message = errorRes.message,
                token = null,
                data = null
            )
        }

        return user
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getAllQuestions(): Flow<PagingData<TriviaQuestion>> {
        val pagingSourceFactory = { questProviderDatabase.questionDao().getAllQuestions() }
        return Pager(
            config = PagingConfig(pageSize = QUESTIONS_PER_PAGE),
            remoteMediator = QuestProviderRemoteMediator(
                questionApi = questionApi,
                questProviderDatabase = questProviderDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}