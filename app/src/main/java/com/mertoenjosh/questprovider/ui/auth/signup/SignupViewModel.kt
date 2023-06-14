package com.mertoenjosh.questprovider.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertoenjosh.questprovider.domain.models.User
import com.mertoenjosh.questprovider.domain.repositories.AuthRepo
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
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {
    private val _signupState = MutableStateFlow(SignupState())
    val signupState get() = _signupState.asStateFlow()

    fun handleSignupEvents(signupEvents: SignupEvents) {
        when (signupEvents) {
            is SignupEvents.FirstNameChanged -> {
                _signupState.update {
                    it.copy(firstName = InputWrapper(signupEvents.firstName))
                }
            }

            is SignupEvents.LastNameChanged -> {
                _signupState.update {
                    it.copy(lastName = InputWrapper(signupEvents.lastName))
                }
            }

            is SignupEvents.EmailChanged -> {
                _signupState.update {
                    it.copy(email = InputWrapper(signupEvents.email))
                }
            }

            is SignupEvents.PasswordChanged -> {
                _signupState.update {
                    it.copy(password = InputWrapper(signupEvents.password))
                }
            }

            is SignupEvents.ConfirmPasswordChanged -> {
                _signupState.update {
                    it.copy(confirmPassword = InputWrapper(signupEvents.confirmPassword))
                }
            }

            is SignupEvents.SignupClicked -> {
                displaySignupInputErrors()
            }
        }
    }

    private fun displaySignupInputErrors() {
        viewModelScope.launch(Dispatchers.Default) {
            when (val inputErrors = getSignupInputErrorsOrNull()) {
                null -> {
                    val signupPayload = User(
                        firstName = signupState.value.firstName.value,
                        lastName = signupState.value.lastName.value,
                        email = signupState.value.email.value,
                        password = signupState.value.password.value,
                        confirmPassword = signupState.value.confirmPassword.value
                    )
                    signup(signupPayload)
                }

                else -> {
                    updateSignupInputErrors(inputErrors)
                }
            }
        }
    }

    private fun updateSignupInputErrors(inputErrors: InputErrors) {
        _signupState.update {
            it.copy(
                firstName = InputWrapper(
                    value = it.firstName.value,
                    errorId = inputErrors.firstNameError
                )
            )
        }
        _signupState.update {
            it.copy(
                lastName = InputWrapper(
                    value = it.lastName.value,
                    errorId = inputErrors.lastNameError
                )
            )
        }
        _signupState.update {
            it.copy(
                email = InputWrapper(
                    value = it.email.value,
                    errorId = inputErrors.emailErrorId
                )
            )
        }
        _signupState.update {
            it.copy(
                password = InputWrapper(
                    value = it.password.value,
                    errorId = inputErrors.passwordErrorId
                )
            )
        }
        _signupState.update {
            it.copy(
                confirmPassword = InputWrapper(
                    value = it.confirmPassword.value,
                    errorId = inputErrors.confirmPasswordErrorId
                )
            )
        }
    }

    private fun getSignupInputErrorsOrNull(): InputErrors? {
        val firstNameErrorId = InputValidator.isNameValid(signupState.value.firstName.value)
        val lastNameErrorId = InputValidator.isNameValid(signupState.value.lastName.value)
        val emailErrorId = InputValidator.isEmailValid(signupState.value.email.value)
        val passwordErrorId = InputValidator.isPasswordValid(signupState.value.password.value)
        val passwordConfirmErrorId = InputValidator.isPasswordConfirmValid(
            signupState.value.confirmPassword.value,
            signupState.value.password.value
        )

        return if (
            firstNameErrorId == null && lastNameErrorId == null &&
            emailErrorId == null && passwordErrorId == null &&
            passwordConfirmErrorId == null
        ) {
            null
        } else {
            InputErrors(
                firstNameError = firstNameErrorId,
                lastNameError = lastNameErrorId,
                emailErrorId = emailErrorId,
                passwordErrorId = passwordErrorId,
                confirmPasswordErrorId = passwordConfirmErrorId
            )
        }
    }

    private fun signup(user: User) {
        _signupState.update { it.copy(uiState = UiState.Loading()) }
        viewModelScope.launch {
            try {
                val registerResponse = authRepo.registerUser(user)

                if (registerResponse.error) {
                    _signupState.update { it.copy(uiState = UiState.Error(registerResponse.message)) }
                } else {
                    _signupState.update { it.copy(uiState = UiState.Success(registerResponse.data)) }
                }
            } catch (e: Exception) {
                _signupState.update {
                    it.copy(uiState = e.localizedMessage?.let { errorMessage ->
                        UiState.Error(errorMessage)
                    })
                }
            }
        }

    }
}