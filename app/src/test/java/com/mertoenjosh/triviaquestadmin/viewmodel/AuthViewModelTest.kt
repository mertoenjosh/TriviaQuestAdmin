package com.mertoenjosh.triviaquestadmin.viewmodel

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class AuthViewModelTest(private val password: String, private val isValid: Boolean) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun passwords() = listOf(
            arrayOf("password", true),
            arrayOf("pass1234", true),
            arrayOf("123Four", true),
            arrayOf("passwd", false)
        )
    }

    @Test
    fun validatePassword() {
        assertEquals(AuthViewModel().isPasswordValid(password), isValid)
    }

}