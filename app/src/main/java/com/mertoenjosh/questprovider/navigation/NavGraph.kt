package com.mertoenjosh.questprovider.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import com.mertoenjosh.questprovider.ui.auth.SignInScreen
import com.mertoenjosh.questprovider.ui.auth.SignUpScreen
import com.mertoenjosh.questprovider.ui.details.AddQuestionScreen
import com.mertoenjosh.questprovider.ui.details.QuestionDetailsScreen
import com.mertoenjosh.questprovider.ui.home.QuestionsScreen
import com.mertoenjosh.questprovider.ui.onboarding.WelcomeScreen
import com.mertoenjosh.questprovider.util.Constants.ARG_QUESTION_ID

@OptIn(ExperimentalPagingApi::class)
@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route // TODO: Fix this
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