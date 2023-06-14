package com.mertoenjosh.questprovider.ui.auth.login

import com.mertoenjosh.questprovider.domain.models.User
import com.mertoenjosh.questprovider.ui.util.UiState
import com.mertoenjosh.questprovider.util.InputWrapper

data class LoginState(
    val email: InputWrapper = InputWrapper(),
    val password: InputWrapper = InputWrapper(),
    val uiState: UiState<User?>? = null
)

sealed class LoginEvents {
    data class EmailChanged(val email: String) : LoginEvents()
    data class PasswordChanged(val password: String) : LoginEvents()
    object LoginClicked : LoginEvents()
}