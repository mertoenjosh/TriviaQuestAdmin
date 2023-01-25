package com.mertoenjosh.triviaquestadmin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.mertoenjosh.triviaquestadmin.data.models.TriviaQuestion
import com.mertoenjosh.triviaquestadmin.data.repos.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: Repository
): ViewModel() {
    val getAllQuestion = repository.getAllQuestions()

    fun onQuestionClick(question: TriviaQuestion) {
        // TODO: Navigate to question details' screen
        Timber.d("MainViewModelTAG: %s", question)
    }
}