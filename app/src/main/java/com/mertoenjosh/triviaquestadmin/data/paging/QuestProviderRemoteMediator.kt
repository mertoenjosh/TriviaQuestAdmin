package com.mertoenjosh.triviaquestadmin.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mertoenjosh.triviaquestadmin.data.local.QuestionsDatabase
import com.mertoenjosh.triviaquestadmin.data.models.TriviaQuestion
import com.mertoenjosh.triviaquestadmin.data.models.TriviaQuestionRemoteKeys
import com.mertoenjosh.triviaquestadmin.data.network.apis.QuestionApi
import com.mertoenjosh.triviaquestadmin.data.repos.mappers.toEntity
import com.mertoenjosh.triviaquestadmin.util.Constants.QUESTIONS_PER_PAGE
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class QuestProviderRemoteMediator @Inject constructor(
    private val questionApi: QuestionApi,
    private val questProviderDatabase: QuestionsDatabase
): RemoteMediator<Int, TriviaQuestion>() {
    private val questionDao = questProviderDatabase.questionDao()
    private val questProviderRemoteKeysDao = questProviderDatabase.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, TriviaQuestion>
    ): MediatorResult {
        return try {
            val currentPage = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = questionApi.getAllQuestions(page = currentPage, perPage = QUESTIONS_PER_PAGE)
            val endOfPaginationReached = response.data.questions.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            questProviderDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    questionDao.deleteAllQuestions()
                    questProviderRemoteKeysDao.deleteAllRemoteKeys()
                }

                val keys = response.data.questions.map { question ->
                    TriviaQuestionRemoteKeys(
                        id = question.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                val questions = mutableListOf<TriviaQuestion>()

                response.data.questions.forEach { data ->
                    val question = data.toEntity()
                    questions.add(question)
                }

                questProviderRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                questionDao.addQuestions(questions = questions)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, TriviaQuestion>
    ): TriviaQuestionRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                questProviderRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, TriviaQuestion>
    ): TriviaQuestionRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { question ->
                questProviderRemoteKeysDao.getRemoteKeys(id = question.id)
            }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, TriviaQuestion>
    ): TriviaQuestionRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { question ->
                questProviderRemoteKeysDao.getRemoteKeys(id = question.id)
            }
    }
}