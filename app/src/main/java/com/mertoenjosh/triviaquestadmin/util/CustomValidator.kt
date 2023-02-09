package com.mertoenjosh.triviaquestadmin.util

import java.util.regex.Pattern


object CustomValidator {
    val errors = mutableListOf<String>()

    fun isInputValid(email: String, password: String): Boolean {
        return isEmailValid(email) &&
                isPasswordValid(password)
    }

    fun isInputValid(firstName: String, lastName: String,email: String, password: String, passwordConfirm: String): Boolean {
        return isNameValid(firstName) &&
                isNameValid(lastName) &&
                isEmailValid(email) &&
                isPasswordValid(passwordConfirm) &&
                isPasswordConfirmValid(password, passwordConfirm)
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

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

        if (email.isEmpty()) {
            errors.add("Email cannot be blank")
            return false
        }
        return emailRegex.toRegex().matches(email)
    }

    private fun isPasswordValid(password: String): Boolean {
        if (password.isEmpty()) {
            errors.add("Password cannot be blank")
            return false
        }

        if (password.length < 6) {
            errors.add("Password length should be more than 6")
            return false
        }

        val letters = Pattern.compile("a-zA-Z")
        val digit = Pattern.compile("0-9")
        val special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")

        val hasLetter = letters.matcher(password)
        val hasDigit = digit.matcher(password)
        val hasSpecial = special.matcher(password)

//        return if (hasLetter.find() && hasDigit.find() && hasSpecial.find()) {
//            true
//        } else {
//            errors.add("Password must contain a letter a digit and special character")
//            false
//        }
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