package com.mertoenjosh.questprovider.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.domain.repositories.QuestionRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val questionRepo: QuestionRepo
) : ViewModel() {
    fun getAllQuestion(): Flow<PagingData<QuestionEntity>> =
        questionRepo.getAllQuestions().cachedIn(viewModelScope)
}