package com.mertoenjosh.questprovider.ui.home

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.data.repositories.mappers.toDomain
import com.mertoenjosh.questprovider.domain.models.Question
import com.mertoenjosh.questprovider.ui.navigation.Screen
import com.mertoenjosh.questprovider.ui.components.CustomMenuDialog
import com.mertoenjosh.questprovider.ui.components.Question
import com.mertoenjosh.questprovider.ui.components.TopAppBar
import com.mertoenjosh.questprovider.ui.theme.QuestProviderTheme


@Composable
fun QuestionsScreen(
    navHostController: NavHostController
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val context = LocalContext.current
    val showDialogMenu = remember { mutableStateOf(false) }

    Scaffold(
        // TopBar
        topBar = {
            TopAppBar(
                title = R.string.trivia_quest,
                profileIcon = Icons.Filled.AccountCircle,
                onProfileOrBackIconClick = { showDialogMenu.value = true }
            )
        },

        // Content
        content = { padding ->
            QuestionsScreenContent(
                modifier = Modifier.padding(padding),
                navHostController = navHostController,
                homeViewModel = homeViewModel,
                context = context,
                showDialogMenu = showDialogMenu
            )
        },
        // FAB
        floatingActionButtonPosition = FabPosition.End, floatingActionButton = {
            FloatingActionButton(onClick = {
                navHostController.navigate(Screen.AddQuestion.route)
            }, contentColor = MaterialTheme.colors.onSecondary, content = {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            })
        }
    )
}

@Composable
fun QuestionsScreenContent(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    navHostController: NavHostController,
    context: Context,
    showDialogMenu: MutableState<Boolean>
) {
    val questions = homeViewModel.getAllQuestion().collectAsLazyPagingItems()

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        QuestionsList(
            questions = questions,
            onQuestionClick = { question ->
                navHostController.navigate(Screen.Details.passQuestion(question.id))
            })

        if (showDialogMenu.value) {
            CustomMenuDialog(title = R.string.trivia_quest,
                onDismiss = { showDialogMenu.value = !showDialogMenu.value },
                name = "Jordan Park",
                email = "hello@jpark.com",
                onAccountImageAndEmailClicked = {},
                onDialogItemClicked = {})
        }
    }
}

@Composable
fun QuestionsList(
    questions: LazyPagingItems<QuestionEntity>, onQuestionClick: (Question) -> Unit
) {
    LazyColumn {
        items(items = questions, key = { question -> question.id }) { question ->
            question?.let {
                Question(question = it.toDomain(),
                    onQuestionClick = { onQuestionClick(question.toDomain()) })
            }
        }

        when (val state = questions.loadState.refresh) {
            is LoadState.Error -> {
                // TODO: Handle error
            }

            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = stringResource(id = R.string.please_wait)
                        )
                        CircularProgressIndicator()
                    }
                }
            }

            else -> {}
        }

        when (val state = questions.loadState.append) {
            is LoadState.Error -> {
                // TODO: Handle error
            }

            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(color = Color.Black)
                    }

                }
            }

            else -> {}
        }
    }
}

@Preview(
    showBackground = true, widthDp = 320
)
@Composable
fun QuestionsListPreview() {
    QuestProviderTheme {
//        QuestionsList(questions = mockQuestions, onQuestionClick = {})
    }
}

@Preview(
    showBackground = true, widthDp = 320, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark"
)
@Preview(
    showBackground = true, widthDp = 320
)
@Composable
fun QuestionsScreenPreview() {
    QuestProviderTheme {
        QuestionsScreen(navHostController = rememberNavController())
    }
}