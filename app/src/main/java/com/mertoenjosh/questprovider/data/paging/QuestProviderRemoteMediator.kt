package com.mertoenjosh.questprovider.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mertoenjosh.questprovider.data.database.QpDatabase
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.data.database.models.QuestionRemoteKeysEntity
import com.mertoenjosh.questprovider.data.network.apis.QuestionApi
import com.mertoenjosh.questprovider.data.repositories.mappers.toEntity
import com.mertoenjosh.questprovider.data.util.Constants.QUESTIONS_PER_PAGE

@OptIn(ExperimentalPagingApi::class)
class QuestProviderRemoteMediator(
    private val questionApi: QuestionApi,
    private val qpDatabase: QpDatabase
) : RemoteMediator<Int, QuestionEntity>() {
    private val questionDao = qpDatabase.questionDao()
    private val questProviderRemoteKeysDao = qpDatabase.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, QuestionEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
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

            val response =
                questionApi.getAllQuestions(page = currentPage, perPage = QUESTIONS_PER_PAGE)
            val endOfPaginationReached = response.data.isEmpty()
//            val endPages = response.data.questions.size < state.config.pageSize

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            qpDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    questionDao.deleteAllQuestions()
                    questProviderRemoteKeysDao.deleteAllRemoteKeys()
                }

                val keys = response.data.map { question ->
                    QuestionRemoteKeysEntity(
                        id = question.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                val questions = response.data.map { data ->
                    data.toEntity()
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
        state: PagingState<Int, QuestionEntity>
    ): QuestionRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                questProviderRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, QuestionEntity>
    ): QuestionRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { question ->
                questProviderRemoteKeysDao.getRemoteKeys(id = question.id)
            }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, QuestionEntity>
    ): QuestionRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { question ->
                questProviderRemoteKeysDao.getRemoteKeys(id = question.id)
            }
    }
}