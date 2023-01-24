package com.mertoenjosh.triviaquestadmin.viewmodel

import androidx.lifecycle.ViewModel
import com.mertoenjosh.triviaquestadmin.data.models.Question
import timber.log.Timber

class MainViewModel(): ViewModel() {

    fun onQuestionClick(question: Question) {
        Timber.tag(TAG).d("onQuestionClick: %s", question.question)
    }


    companion object {
        private const val TAG = "MainViewModelTAG"
    }
}