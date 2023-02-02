package com.mertoenjosh.triviaquestadmin.viewmodel

import androidx.lifecycle.ViewModel
import com.mertoenjosh.triviaquestadmin.data.repos.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(repository: Repository) : ViewModel() {
    val errors = mutableListOf<String>()

    fun loginUser(email: String, password: String): Boolean {
        return !isInputValid(email, password)
    }

    fun registerUser(firstName: String, lastName: String, email: String, password: String, confirmPassword: String): Boolean {
        return !isInputValid(firstName, lastName, email, password,confirmPassword)
    }

    private fun isInputValid(email: String, password: String): Boolean {
        return !isEmailValid(email) ||
                !isPasswordValid(password)
    }

    private fun isInputValid(firstName: String, lastName: String,email: String, password: String, passwordConfirm: String): Boolean {
        return !isFirstNameValid(firstName) ||
                !isLastNameValid(lastName) ||
                !isEmailValid(email) ||
                !isPasswordValid(passwordConfirm) ||
                !isPasswordConfirmValid(password, passwordConfirm)
    }

    private fun isFirstNameValid(firstName: String): Boolean {
        if (firstName.isEmpty()) {
            errors.add("First name cannot be blank")
            return false
        }
        return true
    }

    private fun isLastNameValid(lastName: String): Boolean {
        if (lastName.isEmpty()) {
            errors.add("Last name cannot be blank")
            return false
        }
        return true
    }

    private fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) {
            errors.add("Email cannot be blank")
            return false
        }
        return true
    }

    private fun isPasswordValid(password: String): Boolean {
        if (password.isEmpty()) {
            errors.add("Password cannot be blank")
            return false
        }
        return true
    }

    private fun isPasswordConfirmValid(password: String, confirmPassword: String): Boolean {
        if (password != confirmPassword) {
            errors.add("Passwords do not match")
            return false
        }
        return true
    }

}