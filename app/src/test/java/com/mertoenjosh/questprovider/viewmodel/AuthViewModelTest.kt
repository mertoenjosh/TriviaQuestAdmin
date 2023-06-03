package com.mertoenjosh.questprovider.viewmodel

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class LoginUserTest(private val email: String, private val password: String, private val isValid: Boolean) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun passwords() = listOf(
            arrayOf("Julia.abc@", "password", false),
            arrayOf("Samantha@com", "pass1234", false),
            arrayOf("Samantha_21.", "123Four", false),
            arrayOf("Julia@007.com", "passwd", true),
            arrayOf("_Julia007.com", "passwd", false),
            arrayOf("_Julia007@abc.co.in", "passwd", false),
            arrayOf("Julia.007@abc.com", "passwd", true)
        )
    }

    @Test
    fun loginUserTest() {

    }

}