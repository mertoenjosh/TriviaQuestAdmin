package com.mertoenjosh.questprovider.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.ui.components.MainActionButton
import com.mertoenjosh.questprovider.ui.components.MyOutlinedTextField
import com.mertoenjosh.questprovider.ui.components.MyRadioGroup
import com.mertoenjosh.questprovider.ui.components.MySpinnerDropdown
import com.mertoenjosh.questprovider.ui.components.TopAppBar
import com.mertoenjosh.questprovider.ui.theme.QuestProviderTheme
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
            QuestionDetailsScreenContent(
                modifier = Modifier.padding(paddingValues),
                detailsViewModel = hiltViewModel()
            )
        }
    )
}

@Composable
fun QuestionDetailsScreenContent(
    modifier: Modifier = Modifier,
    detailsViewModel: DetailsViewModel
) {
    val question by detailsViewModel.question.collectAsStateWithLifecycle()
    val correctAnswer by detailsViewModel.correctAnswer.collectAsStateWithLifecycle()
    val choiceOne by detailsViewModel.choiceOne.collectAsStateWithLifecycle()
    val choiceTwo by detailsViewModel.choiceTwo.collectAsStateWithLifecycle()
    val choiceThree by detailsViewModel.choiceThree.collectAsStateWithLifecycle()

    detailsViewModel.logger()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // Question
        MyOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = R.string.question,
            inputWrapper = question,
            onValueChange = {}, // todo
            onImeKeyAction = {}
        )

        // Category spinner
        Row {
            // Todo: Spinner validation
            val categories = listOf("Science", "General Knowledge", "Film", "Fashion") // FIXME: Cache
            MySpinnerDropdown(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                title = R.string.category,
                list = categories,
                onSelectionChanged = { selected ->
                    Timber.i("Selected item: %s", selected)
                }
            )
        }
        // Correct answer

        Row {
            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = R.string.correct_answer,
                inputWrapper = correctAnswer,
                onValueChange = {}, // todo
                onImeKeyAction = {}
            )
        }

        // Wrong 1
        Row {
            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = R.string.wrong_choice_one,
                inputWrapper = choiceOne,
                onValueChange = {}, // todo
                onImeKeyAction = {}
            )
        }

        // Wrong 2
        Row {
            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = R.string.wrong_choice_two,
                inputWrapper = choiceTwo,
                onValueChange = {}, // todo
                onImeKeyAction = {}
            )
        }

        // Wrong 3
        Row {
            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = R.string.wrong_choice_three,
                inputWrapper = choiceThree,
                onValueChange = {}, // todo
                onImeKeyAction = {}
            )
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
    QuestProviderTheme {
        QuestionDetailsScreen(navHostController = rememberNavController())
    }
}