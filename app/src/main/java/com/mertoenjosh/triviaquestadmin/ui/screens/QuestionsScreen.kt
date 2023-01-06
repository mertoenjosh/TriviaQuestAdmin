package com.mertoenjosh.triviaquestadmin.ui.screens

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
import com.mertoenjosh.triviaquestadmin.domain.models.QuestionModel
import com.mertoenjosh.triviaquestadmin.ui.components.CustomMenuDialog
import com.mertoenjosh.triviaquestadmin.ui.components.Question
import com.mertoenjosh.triviaquestadmin.ui.components.TopAppBar
import com.mertoenjosh.triviaquestadmin.util.questions
import com.mertoenjosh.triviaquestadmin.viewmodel.MainViewModel


@Composable
fun QuestionsScreen(viewmodel: MainViewModel) {
    val questionsMock = questions + questions
    val scaffoldState = rememberScaffoldState()
    val showDialogMenu = remember {
        mutableStateOf(false)
    }

    Scaffold(
        // TopBar
        topBar = {
            TopAppBar(
                title = "Trivia Quest",
                icon = Icons.Filled.AccountCircle,
                onIconClick = {
                    showDialogMenu.value = true
                }
            )
        },

        // scaffold state
        scaffoldState = scaffoldState,

        // Content
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
                    .fillMaxSize()
            ) {
                if (questionsMock.isNotEmpty()) {
                    QuestionsList(
                        questions = questionsMock,
                        onQuestionClick = {viewmodel.onQuestionClick(it)}
                    )
                }
                if (showDialogMenu.value) {
                    CustomMenuDialog(title = "Trivia Quest") { showDialogMenu.value = !showDialogMenu.value }
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
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add question button")
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