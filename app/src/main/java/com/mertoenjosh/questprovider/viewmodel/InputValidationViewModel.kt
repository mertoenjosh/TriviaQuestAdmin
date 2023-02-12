package com.mertoenjosh.questprovider.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertoenjosh.questprovider.util.Constants.EMAIL
import com.mertoenjosh.questprovider.util.Constants.PASSWORD
import com.mertoenjosh.questprovider.util.inputValidations.CustomValidator
import com.mertoenjosh.questprovider.util.inputValidations.InputWrapper
import com.mertoenjosh.questprovider.util.inputValidations.ScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class InputValidationViewModel @Inject constructor(
    private val handle: SavedStateHandle
) : ViewModel() {
    val email = handle.getStateFlow(EMAIL, InputWrapper())
    val password = handle.getStateFlow(PASSWORD, InputWrapper())
    val areInputsValid = combine(email, password) {email, password ->
        email.value.isNotEmpty() && email.errorId == null &&
                password.value.isNotEmpty() && password.errorId == null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)

    private val _events = Channel<ScreenEvent>()
    val events = _events.receiveAsFlow()

    fun onEmailEntered(input: String) {
        val errorId = CustomValidator.isEmailValid(input)
        handle[EMAIL] = email.value.copy(value = input, errorId = errorId)
    }

    fun onPasswordEntered(input: String) {
        val errorId = CustomValidator.isPasswordValid(input)
        handle[PASSWORD] = password.value.copy(value = input, errorId = errorId)
    }

    fun onContinueClick() {
        val resId = if (areInputsValid.value) "Success" else "Validation Error"
        _events.trySend(ScreenEvent.ShowToast(resId))
    }
}