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
import com.mertoenjosh.triviaquestadmin.ui.components.*
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
            .padding(16.dp),
    ) {
        // Question
        /*
        Text(
            text = stringResource(id = R.string.question)
        )

         */

        MyOutlinedTextField(modifier = Modifier.fillMaxWidth(), label = R.string.question)

        // Category spinner
        Row {
            /*
            Text(
                text = stringResource(id = R.string.category)
            )

             */
            // Todo: Spinner
            val categories = listOf("Science", "General Knowledge", "Film", "Fashion")
            MySpinnerDropdown(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                title = R.string.category,
                list = categories,
                onSelectionChanged = { selected ->
                    Timber.i("Selected item: %s", selected)

                }
            )
        }
        // Correct answer

        Row {
            /*
            Text(
                text = stringResource(id = R.string.correct_answer)
            )

             */
            MyOutlinedTextField(modifier = Modifier.fillMaxWidth(), label = R.string.correct_answer)
        }

        // Wrong 1
        Row {
            /*
            Text(
                text = stringResource(id = R.string.wrong_choice_one)
            )

             */
            MyOutlinedTextField(modifier = Modifier.fillMaxWidth(), label = R.string.wrong_choice_one)
        }

        // Wrong 2
        Row {
            /*
            Text(
                text = stringResource(id = R.string.wrong_choice_two)
            )

             */
            MyOutlinedTextField(modifier = Modifier.fillMaxWidth(), label = R.string.wrong_choice_two)
        }

        // Wrong 3
        Row {
            /*
            Text(
                text = stringResource(id = R.string.wrong_choice_three)
            )

             */
            MyOutlinedTextField(modifier = Modifier.fillMaxWidth(), label = R.string.wrong_choice_three)
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