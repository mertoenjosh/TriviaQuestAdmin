package com.mertoenjosh.questprovider.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertoenjosh.questprovider.data.models.request.LoginRequest
import com.mertoenjosh.questprovider.data.models.request.UserRequest
import com.mertoenjosh.questprovider.data.repos.Repository
import com.mertoenjosh.questprovider.util.inputValidations.CustomValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    // test@mnt.dev pass1234
    fun loginUser(email: String, password: String) {

        val loginObj = LoginRequest(email = email, password = password)

        Timber.d("Login: %s", loginObj)

        viewModelScope.launch(Dispatchers.Main) {
            val loginResponse = repository.loginUser(loginObj)

            loginResponse.let {
                Timber.d("User %s", it)
            }
        }
    }

    fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        val userObj = UserRequest(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            confirmPassword = confirmPassword
        )

        // TODO: Register user
    }
}