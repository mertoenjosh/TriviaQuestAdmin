package com.mertoenjosh.questprovider.ui.auth.signup

import com.mertoenjosh.questprovider.domain.models.User
import com.mertoenjosh.questprovider.ui.util.UiState
import com.mertoenjosh.questprovider.util.InputWrapper

data class SignupState(
    val firstName: InputWrapper = InputWrapper(),
    val lastName: InputWrapper = InputWrapper(),
    val email: InputWrapper = InputWrapper(),
    val password: InputWrapper = InputWrapper(),
    val confirmPassword: InputWrapper = InputWrapper(),
    val uiState: UiState<User?>? = null
)

sealed class SignupEvents {
    data class FirstNameChanged(val firstName: String) : SignupEvents()
    data class LastNameChanged(val lastName: String) : SignupEvents()
    data class EmailChanged(val email: String) : SignupEvents()
    data class PasswordChanged(val password: String) : SignupEvents()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignupEvents()
    object SignupClicked : SignupEvents()
}