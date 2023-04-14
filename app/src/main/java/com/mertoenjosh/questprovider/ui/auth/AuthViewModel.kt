package com.mertoenjosh.questprovider.ui.auth

import androidx.lifecycle.*
import com.mertoenjosh.questprovider.data.network.models.request.LoginRequest
import com.mertoenjosh.questprovider.data.repositories.RepositoryImpl
import com.mertoenjosh.questprovider.navigation.Screen
import com.mertoenjosh.questprovider.util.Constants
import com.mertoenjosh.questprovider.util.ScreenEvent
import com.mertoenjosh.questprovider.util.Utils.handler
import com.mertoenjosh.questprovider.util.inputValidations.CustomValidator
import com.mertoenjosh.questprovider.util.inputValidations.FocusedTextFieldKey
import com.mertoenjosh.questprovider.util.inputValidations.InputErrors
import com.mertoenjosh.questprovider.util.inputValidations.InputWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: RepositoryImpl,
    private val handle: SavedStateHandle
) : ViewModel() {
    val firstName = handle.getStateFlow(Constants.FIRST_NAME, InputWrapper())
    val lastName = handle.getStateFlow(Constants.LAST_NAME, InputWrapper())
    val email = handle.getStateFlow(Constants.EMAIL, InputWrapper())
    val password = handle.getStateFlow(Constants.PASSWORD, InputWrapper())
    val confirmPassword = handle.getStateFlow(Constants.CONFIRM_PASSWORD, InputWrapper())
    val areSignUpInputsValid = combine(firstName, lastName, email, password, confirmPassword) { firstName, lastName, email, password, confirmPassword ->
        firstName.value.isNotEmpty() &&
                firstName.errorId == null &&
                lastName.value.isNotEmpty() &&
                lastName.errorId == null &&
                email.value.isNotEmpty() &&
                email.errorId == null &&
                password.value.isNotEmpty() &&
                password.errorId == null &&
                confirmPassword.value.isNotEmpty() &&
                confirmPassword.errorId == null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)
    val areSignInInputsValid = combine(email, password ) { email, password ->
        email.value.isNotEmpty() &&
                email.errorId == null &&
                password.value.isNotEmpty() &&
                password.errorId == null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)

    private val _events = Channel<ScreenEvent>()
    val events = _events.receiveAsFlow()

    private var focusedTextField = handle["focusedTextField"] ?: FocusedTextFieldKey.FIRST_NAME
        set(value) {
            field = value
            handle["focusedTextField"] = value
        }

    init {
        if (focusedTextField != FocusedTextFieldKey.NONE) focusOnLastSelectedTextField()
    }

    // test@mnt.dev pass1234
    fun loginUser(loginObj: LoginRequest) {
        viewModelScope.launch(handler) {
            _events.send(ScreenEvent.ShowLoader(true))
            val loginResponse = repository.loginUser(loginObj)
            loginResponse.let { data->
                Timber.d("User %s", data)

                _events.send(ScreenEvent.ShowLoader(false))

                if (!data.token.isNullOrBlank()) {
                    _events.send(ScreenEvent.Navigate(Screen.Home.route))
                } else {
                    _events.send(ScreenEvent.ShowToast("${data.message}"))
                }
            }
        }
    }
    fun onFirstNameEntered(input: String) {
        val errorId = CustomValidator.isNameValid(input)
        handle[Constants.FIRST_NAME] = firstName.value.copy(value = input, errorId = errorId)
    }
    fun onLastNameEntered(input: String) {
        val errorId = CustomValidator.isNameValid(input)
        handle[Constants.LAST_NAME] = lastName.value.copy(value = input, errorId = errorId)
    }
    fun onEmailEntered(input: String) {
        val errorId = CustomValidator.isEmailValid(input)
        handle[Constants.EMAIL] = email.value.copy(value = input, errorId = errorId)
    }
    fun onPasswordEntered(input: String) {
        val errorId = CustomValidator.isPasswordValid(input)
        handle[Constants.PASSWORD] = password.value.copy(value = input, errorId = errorId)
    }
    fun onConfirmPasswordEntered(input: String, password: String) {
        val errorId = CustomValidator.isPasswordConfirmValid(input, password)
        handle[Constants.CONFIRM_PASSWORD] = confirmPassword.value.copy(value = input, errorId = errorId)
    }
    fun onTextFieldFocusChanged(key: FocusedTextFieldKey, isFocused: Boolean) {
        focusedTextField = if (isFocused) key else FocusedTextFieldKey.NONE
    }
    fun onNameImeActionClick() {
        _events.trySend(ScreenEvent.MoveFocus())
    }
    fun onContinueClick() {
        viewModelScope.launch(Dispatchers.Default) {
            when (val inputErrors = getSignUpInputErrorsOrNull()) {
                null -> {
                    clearFocusAndHideKeyboard()
                }
                else -> displaySignUpInputErrors(inputErrors)
            }
        }
    }

    private fun displaySignUpInputErrors(inputErrors: InputErrors) {
        handle[Constants.FIRST_NAME] = firstName.value.copy(errorId = inputErrors.firstNameError)
        handle[Constants.LAST_NAME] = lastName.value.copy(errorId = inputErrors.lastNameError)
        handle[Constants.EMAIL] = email.value.copy(errorId = inputErrors.emailErrorId)
        handle[Constants.PASSWORD] = password.value.copy(errorId = inputErrors.passwordErrorId)
        handle[Constants.CONFIRM_PASSWORD] = password.value.copy(errorId = inputErrors.confirmPasswordErrorId)
    }

    private fun getSignUpInputErrorsOrNull(): InputErrors? {
        val firstNameErrorId = CustomValidator.isNameValid(firstName.value.value)
        val lastNameErrorId = CustomValidator.isNameValid(lastName.value.value)
        val emailErrorId = CustomValidator.isEmailValid(email.value.value)
        val passwordErrorId = CustomValidator.isPasswordValid(password.value.value)
        val confirmPasswordErrorId = CustomValidator.isPasswordConfirmValid(password.value.value, confirmPassword.value.value)
        return if (emailErrorId == null && passwordErrorId == null) {
            null
        } else {
            InputErrors(
                firstNameError = firstNameErrorId,
                lastNameError = lastNameErrorId,
                emailErrorId = emailErrorId,
                passwordErrorId = passwordErrorId,
                confirmPasswordErrorId = confirmPasswordErrorId
            )
        }
    }
    private suspend fun clearFocusAndHideKeyboard() {
        _events.send(ScreenEvent.ClearFocus)
        _events.send(ScreenEvent.UpdateKeyboard(false))
        focusedTextField = FocusedTextFieldKey.NONE
    }

    private fun focusOnLastSelectedTextField() {
        viewModelScope.launch(Dispatchers.Default) {
            _events.send(ScreenEvent.RequestFocus(focusedTextField))
            delay(250)
            _events.send(ScreenEvent.UpdateKeyboard(true))
        }
    }
}