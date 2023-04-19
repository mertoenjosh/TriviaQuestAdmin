package com.mertoenjosh.questprovider.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mertoenjosh.questprovider.util.Constants
import com.mertoenjosh.questprovider.util.Constants.ARG_QUESTION_ID
import com.mertoenjosh.questprovider.util.inputValidations.InputWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val handle: SavedStateHandle
) : ViewModel() {
    val question = handle.getStateFlow(Constants.QUESTION, InputWrapper())
    val correctAnswer = handle.getStateFlow(Constants.CORRECT_ANSWER, InputWrapper())
    val choiceOne = handle.getStateFlow(Constants.CHOICE_ONE, InputWrapper())
    val choiceTwo = handle.getStateFlow(Constants.CHOICE_TWO, InputWrapper())
    val choiceThree = handle.getStateFlow(Constants.CHOICE_THREE, InputWrapper())

    fun logger() {
        Timber.d("Args Ext: %s", handle[ARG_QUESTION_ID])
    }
}