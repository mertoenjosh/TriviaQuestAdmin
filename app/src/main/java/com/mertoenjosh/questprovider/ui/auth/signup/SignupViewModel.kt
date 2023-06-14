package com.mertoenjosh.questprovider.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertoenjosh.questprovider.domain.repositories.AuthRepo
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
class SignupViewModel @Inject constructor(
    val authRepo: AuthRepo
) : ViewModel() {
    val signupState = MutableStateFlow(SignupState())

    fun handleSignupEvents(signupEvents: SignupEvents) {
        when (signupEvents) {
            is SignupEvents.FirstNameChanged -> {
                signupState.update {
                    it.copy(firstName = InputWrapper(signupEvents.firstName))
                }
            }

            is SignupEvents.LastNameChanged -> {
                signupState.update {
                    it.copy(lastName = InputWrapper(signupEvents.lastName))
                }
            }

            is SignupEvents.EmailChanged -> {
                signupState.update {
                    it.copy(email = InputWrapper(signupEvents.email))
                }
            }

            is SignupEvents.PasswordChanged -> {
                signupState.update {
                    it.copy(password = InputWrapper(signupEvents.password))
                }
            }

            is SignupEvents.ConfirmPasswordChanged -> {
                signupState.update {
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
                    signup()
                }

                else -> {
                    updateSignupInputErrors(inputErrors)
                }
            }
        }
    }

    private fun updateSignupInputErrors(inputErrors: InputErrors) {
        signupState.update {
            it.copy(
                firstName = InputWrapper(
                    value = it.firstName.value,
                    errorId = inputErrors.firstNameError
                )
            )
        }
        signupState.update {
            it.copy(
                lastName = InputWrapper(
                    value = it.lastName.value,
                    errorId = inputErrors.lastNameError
                )
            )
        }
        signupState.update {
            it.copy(
                email = InputWrapper(
                    value = it.email.value,
                    errorId = inputErrors.emailErrorId
                )
            )
        }
        signupState.update {
            it.copy(
                password = InputWrapper(
                    value = it.password.value,
                    errorId = inputErrors.passwordErrorId
                )
            )
        }
        signupState.update {
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
            emailErrorId == null && passwordErrorId != null &&
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

    private fun signup() {

    }
}