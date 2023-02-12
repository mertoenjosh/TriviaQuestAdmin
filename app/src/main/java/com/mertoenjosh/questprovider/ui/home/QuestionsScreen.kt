package com.mertoenjosh.questprovider.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.data.models.TriviaQuestion
import com.mertoenjosh.questprovider.navigation.Screen
import com.mertoenjosh.questprovider.theme.QuestProviderTheme
import com.mertoenjosh.questprovider.ui.components.CustomMenuDialog
import com.mertoenjosh.questprovider.ui.components.Question
import com.mertoenjosh.questprovider.ui.components.TopAppBar
import com.mertoenjosh.questprovider.viewmodel.HomeViewModel
import timber.log.Timber


@OptIn(ExperimentalPagingApi::class)
@Composable
fun QuestionsScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val getAllQuestions = homeViewModel.getAllQuestion.collectAsLazyPagingItems()

    val scaffoldState = rememberScaffoldState()
    val showDialogMenu = remember {
        mutableStateOf(false)
    }

    Scaffold(
        // TopBar
        topBar = {
            TopAppBar(
                title = R.string.trivia_quest,
                profileIcon = Icons.Filled.AccountCircle,
                onProfileOrBackIconClick = {
                    showDialogMenu.value = true
                }
            )
        },

        // scaffold state
        scaffoldState = scaffoldState,

        // Content
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                QuestionsList(
                    questions = getAllQuestions,
                    onQuestionClick = { question ->
                        homeViewModel.onQuestionClick(question)
                    }
                )

                if (showDialogMenu.value) {
                    CustomMenuDialog(
                        title = R.string.trivia_quest,
                        onDismiss =  { showDialogMenu.value = !showDialogMenu.value },
                        name = "Jordan Park",
                        email = "hello@jpark.com",
                        onAccountImageAndEmailClicked = {},
                        onDialogItemClicked = {}
                    )
                }
            }
        },
        // FAB
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    /* TODO: Navigate to new question's page */
                    navHostController.navigate(Screen.Details.route)
                },
                contentColor = MaterialTheme.colors.background,
                content = {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            )
        }
    )

}

@Composable
fun QuestionsList(questions: LazyPagingItems<TriviaQuestion>, onQuestionClick: (TriviaQuestion)->Unit) {

    LazyColumn{
        items(items = questions, key = { question -> question.id}) { question ->
            question?.let {
                Timber.i("Question: %s", it)

                Question(
                    question = it,
                    onQuestionClick = { onQuestionClick(question) }
                )
            }
        }
    }
}

@Preview (
    showBackground = true,
    widthDp = 320
)
@Composable
fun QuestionsListPreview() {
    QuestProviderTheme {
//        QuestionsList(questions = mockQuestions, onQuestionClick = {})
    }
}

@OptIn(ExperimentalPagingApi::class)
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview (
    showBackground = true,
    widthDp = 320
)
@Composable
fun QuestionsScreenPreview() {
    QuestProviderTheme {
         QuestionsScreen(navHostController = rememberNavController())
    }
}