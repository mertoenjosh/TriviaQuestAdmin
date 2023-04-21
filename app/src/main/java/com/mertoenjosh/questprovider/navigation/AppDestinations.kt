package com.mertoenjosh.questprovider.navigation

import com.mertoenjosh.questprovider.util.Constants.ARG_QUESTION_ID

sealed class Screen(val route: String) {
    object Welcome: Screen(route = "welcome_screen")
    object SignUp: Screen(route = "sign_up_screen")
    object SignIn: Screen(route = "sign_in_screen")
    object Home: Screen(route = "questions_screen")
    object Details: Screen(route = "question_details_screen?id={$ARG_QUESTION_ID}") {
        fun passQuestion(questionId: String): String = "question_details_screen?id=$questionId"
    }
    object AddQuestion: Screen(route = "add_new_question_screen")
}
