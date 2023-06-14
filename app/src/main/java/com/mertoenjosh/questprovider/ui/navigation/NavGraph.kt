package com.mertoenjosh.questprovider.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mertoenjosh.questprovider.ui.auth.login.SignInScreen
import com.mertoenjosh.questprovider.ui.auth.signup.SignUpScreen
import com.mertoenjosh.questprovider.ui.details.AddQuestionScreen
import com.mertoenjosh.questprovider.ui.details.QuestionDetailsScreen
import com.mertoenjosh.questprovider.ui.home.QuestionsScreen
import com.mertoenjosh.questprovider.ui.onboarding.WelcomeScreen
import com.mertoenjosh.questprovider.util.Constants.ARG_QUESTION_ID

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
            route = Screen.Details.route,
            arguments = listOf(navArgument(ARG_QUESTION_ID) {
                type = NavType.StringType
                defaultValue = ""
            })
        ) {
            QuestionDetailsScreen(navHostController = navHostController)
        }

        composable(
            route = Screen.AddQuestion.route
        ) {
            AddQuestionScreen(navHostController = navHostController)
        }

    }
}