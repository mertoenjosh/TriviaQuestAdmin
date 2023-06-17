package com.mertoenjosh.questprovider.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertoenjosh.questprovider.domain.models.User
import com.mertoenjosh.questprovider.domain.repositories.AuthRepo
import com.mertoenjosh.questprovider.ui.auth.util.InputErrors
import com.mertoenjosh.questprovider.ui.util.InputValidator
import com.mertoenjosh.questprovider.ui.util.InputWrapper
import com.mertoenjosh.questprovider.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepo: AuthRepo,
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
                    val loginPayload = User(
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
    private fun login(user: User) {
        _loginState.update { it.copy(uiState = UiState.Loading()) }
        viewModelScope.launch {
            try {
                val loginResponse = authRepo.loginUser(user)
                if (loginResponse.error) {
                    Timber.e("Login Exception: %s", loginResponse.message)

                    _loginState.update { it.copy(uiState = UiState.Error(loginResponse.message)) }
                } else {
                    Timber.i("Logged in....")

                    _loginState.update { it.copy(uiState = UiState.Success(loginResponse.data)) }
                }
            } catch (e: Exception) {
                Timber.e(e, "Login Exception: %s", e.message)

                _loginState.update {
                    it.copy(uiState = e.localizedMessage?.let { errorMessage ->
                        UiState.Error(errorMessage)
                    })
                }
            }
        }
    }
}