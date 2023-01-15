package com.mertoenjosh.triviaquestadmin.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertoenjosh.triviaquestadmin.R
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme
import com.mertoenjosh.triviaquestadmin.ui.components.MainActionButton
import com.mertoenjosh.triviaquestadmin.ui.components.MyRadioGroup
import com.mertoenjosh.triviaquestadmin.ui.components.MyTextField
import com.mertoenjosh.triviaquestadmin.ui.components.TopAppBar
import timber.log.Timber

@Composable
fun QuestionDetailsScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                showBackIcon = true,
                title = R.string.add_question,
                onProfileOrBackIconClick = {
                    navHostController.popBackStack()
                }
            )
        },
        content = { paddingValues ->
            QuestionDetailsScreenContent(modifier = Modifier.padding(paddingValues))
        }
    )
}

@Composable
fun QuestionDetailsScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Question
        Text(
            text = stringResource(id = R.string.question)
        )

        MyTextField(modifier = Modifier.fillMaxWidth())

        // Category spinner
        Row {
            Text(
                text = stringResource(id = R.string.category)
            )
            // Todo: Spinner
        }
        // Correct answer

        Row {
            Text(
                text = stringResource(id = R.string.correct_answer)
            )
            MyTextField()
        }

        // Wrong 1
        Row {
            Text(
                text = stringResource(id = R.string.wrong_choice_one)
            )
            MyTextField()
        }

        // Wrong 2
        Row {
            Text(
                text = stringResource(id = R.string.wrong_choice_two)
            )
            MyTextField()
        }

        // Wrong 3
        Row {
            Text(
                text = stringResource(id = R.string.wrong_choice_three)
            )
            MyTextField()
        }

        // Difficulty radio group
        Text(
            text = stringResource(id = R.string.difficulty)
        )
        MyRadioGroup()

        // Save Button
        MainActionButton(text = R.string.save) {
            // Save or edit
            Timber.tag("SaveButton").i("Save Clicked")
        }

    }
}

@Preview(
    showBackground = true,
    name = "Question details screen"
)
@Composable
fun QuestionDetailsScreenPreview() {
    TriviaQuestAdminTheme {
        QuestionDetailsScreen(navHostController = rememberNavController())
    }
}