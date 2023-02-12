package com.mertoenjosh.questprovider.util.inputValidations

import com.mertoenjosh.questprovider.R
import java.util.regex.Pattern


object CustomValidator {
    val errors = mutableListOf<String>()

    fun isInputValid(email: String, password: String): Boolean {
//        return isEmailValid(email) &&
//                isPasswordValid(password)
        return false
    }

    fun isInputValid(firstName: String, lastName: String,email: String, password: String, passwordConfirm: String): Boolean {
//        return isNameValid(firstName) &&
//                isNameValid(lastName) &&
//                isEmailValid(email) &&
//                isPasswordValid(passwordConfirm) &&
//                isPasswordConfirmValid(password, passwordConfirm)
        return false
    }

    private fun isNameValid(name: String): Boolean {
        val special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")

        return if (name.trim().isEmpty()) {
            errors.add("Name cannot be blank")
            false
        } else if (special.matcher(name).find()) {
            errors.add("Name cannot contain special characters")
            false
        } else {
            true
        }
    }

    fun isEmailValid(email: String): Int? {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

        return when {
            email.isEmpty() -> R.string.email_cannot_be_blank

            !(emailRegex.toRegex().matches(email)) -> R.string.enter_valid_email

            else -> null
        }
    }

    fun isPasswordValid(password: String): Int? {

        return when {
            password.length < 5 -> R.string.password_length_should_be_more_than_5

            else -> null
        }
    }

    private fun isPasswordConfirmValid(password: String, confirmPassword: String): Boolean {
        if (password != confirmPassword) {
            errors.add("Passwords do not match")
            return false
        }
        return true
    }
}