package com.mertoenjosh.triviaquestadmin.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mertoenjosh.triviaquestadmin.domain.models.QuestionModel

class MainViewModel(): ViewModel() {

    fun onQuestionClick(question: QuestionModel) {
        Log.d(TAG, "onQuestionClick: ${question.question}")
    }


    companion object {
        private const val TAG = "MainViewModelTAG"
    }
}