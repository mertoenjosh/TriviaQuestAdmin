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
import com.mertoenjosh.questprovider.ui.components.MyRadioGroup
import com.mertoenjosh.questprovider.ui.components.MySpinnerDropdown
import com.mertoenjosh.questprovider.ui.components.TopAppBar
import com.mertoenjosh.questprovider.ui.theme.QuestProviderTheme
import timber.log.Timber

@Composable
fun QuestionDetailsScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(showBackIcon = true,
            title = R.string.question_details,
            onProfileOrBackIconClick = {
                navHostController.popBackStack()
            })
    }, content = { paddingValues ->
        QuestionDetailsScreenContent(
            modifier = Modifier.padding(paddingValues), detailsViewModel = hiltViewModel()
        )
    })
}

@Composable
fun QuestionDetailsScreenContent(
    modifier: Modifier = Modifier, detailsViewModel: DetailsViewModel
) {
    val questionId by detailsViewModel.questionId.collectAsStateWithLifecycle()
    detailsViewModel.fetchQuestion(questionId)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // Question
        Text(text = stringResource(R.string.question))
        Text(
            text = detailsViewModel.quiz.question, modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        // Category spinner
        Row {
            // Todo: Spinner validation
            val categories =
                listOf("Science", "General Knowledge", "Film", "Fashion") // FIXME: Cache
            MySpinnerDropdown(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                title = R.string.category,
                list = categories,
                onSelectionChanged = { selected ->
                    Timber.i("Selected item: %s", selected)
                })
        }

        // Correct answer
        Text(text = stringResource(R.string.correct_answer))
        Text(
            text = detailsViewModel.quiz.correctAnswer,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        // Wrong 1
        Text(text = stringResource(R.string.wrong_choice_one))

        Text(
            text = detailsViewModel.quiz.choices[0],
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        // Wrong 2
        Text(text = stringResource(R.string.wrong_choice_two))
        Text(
            text = detailsViewModel.quiz.choices[1],
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        // Wrong 3
        Text(text = stringResource(R.string.wrong_choice_three))
        Text(
            text = detailsViewModel.quiz.choices[2],
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        // Difficulty radio group
        Text(
            text = stringResource(id = R.string.difficulty)
        )
        MyRadioGroup(selected = detailsViewModel.quiz.difficulty)
    }
}

@Preview(
    showBackground = true, name = "Question details screen"
)
@Composable
fun QuestionDetailsScreenPreview() {
    QuestProviderTheme {
        QuestionDetailsScreen(navHostController = rememberNavController())
    }
}