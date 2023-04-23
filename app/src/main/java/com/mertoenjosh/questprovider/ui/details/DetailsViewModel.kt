package com.mertoenjosh.questprovider.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertoenjosh.questprovider.domain.models.Question
import com.mertoenjosh.questprovider.domain.repositories.Repository
import com.mertoenjosh.questprovider.util.Constants
import com.mertoenjosh.questprovider.util.inputValidations.InputWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    handle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {
    val question = handle.getStateFlow(Constants.QUESTION, InputWrapper())
    val correctAnswer = handle.getStateFlow(Constants.CORRECT_ANSWER, InputWrapper())
    val choiceOne = handle.getStateFlow(Constants.CHOICE_ONE, InputWrapper())
    val choiceTwo = handle.getStateFlow(Constants.CHOICE_TWO, InputWrapper())
    val choiceThree = handle.getStateFlow(Constants.CHOICE_THREE, InputWrapper())
    val questionId = handle.getStateFlow(Constants.ARG_QUESTION_ID, "")
    var quiz = Question()

    fun fetchQuestion(id: String) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                quiz = repository.getQuestionById(id)
            }
            Timber.e("QUIZ:--- > %s", quiz)
        }
    }
}