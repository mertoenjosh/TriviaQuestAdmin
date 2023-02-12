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
        user =  if (response.isSuccessful) {
            Timber.d("RESPONSE SUCCESS-------> %s", response.body()!!)
            response.body()!!
        } else {
            Timber.d("RESPONSE FAIL ---------> %s",response.body()!!)
            response.body()!!
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