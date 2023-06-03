package com.mertoenjosh.questprovider.util

import com.mertoenjosh.questprovider.R
import java.util.regex.Pattern


object InputValidator {
    fun isNameValid(name: String): Int? {
        val special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")

        return when {
            (name.trim().isEmpty()) -> R.string.name_cannot_be_blank
            (special.matcher(name).find()) -> R.string.name_cannot_have_special_characters
            else -> null
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
            password.length < 5 -> R.string.password_is_too_short
            else -> null
        }
    }

    fun isPasswordConfirmValid(confirmPassword: String, password: String): Int? {
        return when {
            (confirmPassword.length <= 5) -> R.string.password_is_too_short
            (password != confirmPassword) -> R.string.passwords_do_not_match
            else -> null
        }
    }
}