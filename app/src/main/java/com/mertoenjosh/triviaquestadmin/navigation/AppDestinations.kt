package com.mertoenjosh.triviaquestadmin.navigation

sealed class Screen(val route: String) {
    object Welcome: Screen(route = "welcome_screen")
    object SignUp: Screen(route = "sign_up_screen")
    object SignIn: Screen(route = "sign_in_screen")
    object Home: Screen(route = "questions_screen")
    object Details: Screen(route = "question_details_screen")
}
