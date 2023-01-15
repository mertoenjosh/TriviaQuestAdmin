package com.mertoenjosh.triviaquestadmin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertoenjosh.triviaquestadmin.ui.auth.SignInScreen
import com.mertoenjosh.triviaquestadmin.ui.auth.SignUpScreen
import com.mertoenjosh.triviaquestadmin.ui.home.QuestionsScreen
import com.mertoenjosh.triviaquestadmin.ui.onboarding.WelcomeScreen

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController,
        startDestination = Screen.Welcome.route
    ) {
        composable(
            route = Screen.Welcome.route
        ) {
            WelcomeScreen(navHostController = navHostController)
        }

        composable(
            route = Screen.SignUp.route
        ) {
            SignUpScreen(navHostController = navHostController)
        }

        composable(
            route = Screen.SignIn.route
        ) {
            SignInScreen(navHostController = navHostController)
        }

        composable(
            route = Screen.Home.route
        ) {
            QuestionsScreen()
        }
    }
}