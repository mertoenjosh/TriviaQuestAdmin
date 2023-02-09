package com.mertoenjosh.triviaquestadmin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertoenjosh.triviaquestadmin.data.models.request.LoginRequest
import com.mertoenjosh.triviaquestadmin.data.models.request.UserRequest
import com.mertoenjosh.triviaquestadmin.data.repos.Repository
import com.mertoenjosh.triviaquestadmin.util.CustomValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val errors = CustomValidator.errors

    // test@mnt.dev pass1234
    fun loginUser(email: String, password: String) {
//        if (CustomValidator.isInputValid(email, password)) {
            val loginObj = LoginRequest(email = email, password = password)

            Timber.d("Login: %s", loginObj)

            viewModelScope.launch(Dispatchers.Main) {
                val loginResponse = repository.loginUser(loginObj)

                loginResponse?.let {
                    Timber.d("User %s", it)
                }

            }
//        }
    }

    fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return if (!CustomValidator.isInputValid(firstName, lastName, email, password,confirmPassword)) {
            val userObj = UserRequest(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )

            Timber.d("User: %s", userObj)

            true
        } else {
            false
        }
    }
}