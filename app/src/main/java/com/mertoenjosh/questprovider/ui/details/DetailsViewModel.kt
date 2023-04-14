package com.mertoenjosh.questprovider.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mertoenjosh.questprovider.util.Constants
import com.mertoenjosh.questprovider.util.inputValidations.InputWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    handle: SavedStateHandle
) : ViewModel() {
    val question = handle.getStateFlow(Constants.QUESTION, InputWrapper())
    val correctAnswer = handle.getStateFlow(Constants.CORRECT_ANSWER, InputWrapper())
    val choiceOne = handle.getStateFlow(Constants.CHOICE_ONE, InputWrapper())
    val choiceTwo = handle.getStateFlow(Constants.CHOICE_TWO, InputWrapper())
    val choiceThree = handle.getStateFlow(Constants.CHOICE_THREE, InputWrapper())
}