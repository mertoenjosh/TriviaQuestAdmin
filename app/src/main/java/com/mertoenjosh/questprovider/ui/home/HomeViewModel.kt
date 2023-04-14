package com.mertoenjosh.questprovider.ui.home

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.mertoenjosh.questprovider.domain.models.Question
import com.mertoenjosh.questprovider.data.repositories.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: RepositoryImpl
): ViewModel() {
    val getAllQuestion = repository.getAllQuestions()

    fun onQuestionClick(question: Question) {
        // TODO: Navigate to question details' screen
        Timber.d("MainViewModelTAG: %s", question)
    }
}