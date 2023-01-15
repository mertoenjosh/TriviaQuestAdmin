package com.mertoenjosh.triviaquestadmin.ui.home

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
import com.mertoenjosh.triviaquestadmin.R
import com.mertoenjosh.triviaquestadmin.domain.models.QuestionModel
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme
import com.mertoenjosh.triviaquestadmin.ui.components.CustomMenuDialog
import com.mertoenjosh.triviaquestadmin.ui.components.Question
import com.mertoenjosh.triviaquestadmin.ui.components.TopAppBar
import com.mertoenjosh.triviaquestadmin.util.mockQuestions


@Composable
fun QuestionsScreen( /* viewmodel: MainViewModel */ ) {
    val questionList = mockQuestions + mockQuestions
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
                if (questionList.isNotEmpty()) {
                    QuestionsList(
                        questions = questionList,
                        onQuestionClick = { /* viewmodel.onQuestionClick(it) */ }
                    )
                }
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
                onClick = { },
                contentColor = MaterialTheme.colors.background,
                content = {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            )
        }
    )

}

@Composable
fun QuestionsList(questions: List<QuestionModel>, onQuestionClick: (QuestionModel)->Unit) {
    LazyColumn{

        items(questions.size) { questionIndex ->
            val question = questions[questionIndex]

            Question(
                question = question,
                onQuestionClick = onQuestionClick
            )
        }

    }
}

@Preview (
    showBackground = true,
    widthDp = 320
)
@Composable
fun QuestionsListPreview() {
    TriviaQuestAdminTheme {
        QuestionsList(questions = mockQuestions, onQuestionClick = {})
    }
}

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
    TriviaQuestAdminTheme {
         QuestionsScreen()
    }
}