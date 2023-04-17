package com.mertoenjosh.questprovider.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import com.mertoenjosh.questprovider.ui.auth.SignInScreen
import com.mertoenjosh.questprovider.ui.auth.SignUpScreen
import com.mertoenjosh.questprovider.ui.details.QuestionDetailsScreen
import com.mertoenjosh.questprovider.ui.home.QuestionsScreen
import com.mertoenjosh.questprovider.ui.onboarding.WelcomeScreen

@OptIn(ExperimentalPagingApi::class)
@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
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
            QuestionsScreen(navHostController = navHostController)
        }

        composable(
            route = Screen.Details.route
        ) {
            QuestionDetailsScreen(navHostController = navHostController)
        }
    }
}