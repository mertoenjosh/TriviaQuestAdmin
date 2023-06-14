package com.mertoenjosh.questprovider.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertoenjosh.questprovider.data.network.models.payload.Login
import com.mertoenjosh.questprovider.domain.repositories.QuestionRepo
import com.mertoenjosh.questprovider.ui.auth.util.InputErrors
import com.mertoenjosh.questprovider.ui.util.UiState
import com.mertoenjosh.questprovider.util.InputValidator
import com.mertoenjosh.questprovider.util.InputWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: QuestionRepo,
) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState get() = _loginState.asStateFlow()


    fun handleLoginEvents(loginEvents: LoginEvents) {
        when (loginEvents) {
            is LoginEvents.EmailChanged -> {
                _loginState.update {
//                    val errorId = CustomValidator.isEmailValid(loginEvents.email)
                    it.copy(email = InputWrapper(loginEvents.email))
                }
            }

            is LoginEvents.PasswordChanged -> {
                _loginState.update {
//                    val errorId = CustomValidator.isPasswordValid(loginEvents.password)
                    it.copy(password = InputWrapper(loginEvents.password))
                }
            }

            is LoginEvents.LoginClicked -> {
                displayLoginInputErrors()
            }
        }
    }

    private fun displayLoginInputErrors() {
        viewModelScope.launch(Dispatchers.Default) {
            when (val inputErrors = getLoginInputErrorsOrNull()) {
                null -> {
                    // TODO: clear fields, close keyboard
                    val loginPayload = Login(
                        email = loginState.value.email.value,
                        password = loginState.value.password.value
                    )
                    login(loginPayload)
                }

                else -> updateLoginInputErrors(inputErrors)
            }
        }
    }

    private fun updateLoginInputErrors(inputErrors: InputErrors) {
        _loginState.update {
            it.copy(
                email = InputWrapper(
                    value = it.email.value, errorId = inputErrors.emailErrorId
                )
            )
        }
        _loginState.update {
            it.copy(
                password = InputWrapper(
                    value = it.password.value, errorId = inputErrors.passwordErrorId
                )
            )
        }
    }

    private fun getLoginInputErrorsOrNull(): InputErrors? {
        val emailErrorId = InputValidator.isEmailValid(loginState.value.email.value)
        val passwordErrorId = InputValidator.isPasswordValid(loginState.value.password.value)
        return if (emailErrorId == null && passwordErrorId == null) {
            null
        } else {
            InputErrors(
                firstNameError = null,
                lastNameError = null,
                emailErrorId = emailErrorId,
                passwordErrorId = passwordErrorId,
                confirmPasswordErrorId = null
            )
        }
    }

    // test@mnt.dev pass1234
    private fun login(user: Login) {
        _loginState.update { it.copy(uiState = UiState.Loading()) }
        viewModelScope.launch {
            try {
                val loginResponse = repository.loginUser(user)
                if (loginResponse.error) {
                    _loginState.update { it.copy(uiState = UiState.Error(loginResponse.message)) }
                } else {
                    _loginState.update { it.copy(uiState = UiState.Success(loginResponse.data)) }
                }
            } catch (e: Exception) {
                _loginState.update {
                    it.copy(uiState = e.localizedMessage?.let { errorMessage ->
                        UiState.Error(errorMessage)
                    })
                }
            }
        }
    }
}