package com.mertoenjosh.questprovider.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertoenjosh.questprovider.domain.repositories.Repository
import com.mertoenjosh.questprovider.ui.auth.util.InputErrors
import com.mertoenjosh.questprovider.util.InputValidator
import com.mertoenjosh.questprovider.util.InputWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    val loginState = MutableStateFlow(LoginState())

    fun handleLoginEvents(loginEvents: LoginEvents) {
        when (loginEvents) {
            is LoginEvents.EmailChanged -> {
                loginState.update {
//                    val errorId = CustomValidator.isEmailValid(loginEvents.email)
                    it.copy(email = InputWrapper(loginEvents.email))
                }
            }

            is LoginEvents.PasswordChanged -> {
                loginState.update {
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
                    // TODO: clear focus
                }

                else -> updateLoginInputErrors(inputErrors)
            }
        }
    }

    private fun updateLoginInputErrors(inputErrors: InputErrors) {
        loginState.update {
            it.copy(
                email = InputWrapper(
                    value = it.email.value,
                    errorId = inputErrors.emailErrorId
                )
            )
        }
        loginState.update {
            it.copy(
                password = InputWrapper(
                    value = it.password.value,
                    errorId = inputErrors.passwordErrorId
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
    private fun login() {

    }
}