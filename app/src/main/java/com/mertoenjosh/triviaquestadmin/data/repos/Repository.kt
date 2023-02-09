package com.mertoenjosh.triviaquestadmin.data.repos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mertoenjosh.triviaquestadmin.data.database.QuestionsDatabase
import com.mertoenjosh.triviaquestadmin.data.models.TriviaQuestion
import com.mertoenjosh.triviaquestadmin.data.models.request.LoginRequest
import com.mertoenjosh.triviaquestadmin.data.models.response.UserResponse
import com.mertoenjosh.triviaquestadmin.data.network.apis.AuthApi
import com.mertoenjosh.triviaquestadmin.data.network.apis.QuestionApi
import com.mertoenjosh.triviaquestadmin.data.paging.QuestProviderRemoteMediator
import com.mertoenjosh.triviaquestadmin.util.Constants.QUESTIONS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
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