package com.mertoenjosh.questprovider.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.ui.components.MainActionButton
import com.mertoenjosh.questprovider.ui.components.MyOutlinedTextField
import com.mertoenjosh.questprovider.ui.components.MyRadioGroup
import com.mertoenjosh.questprovider.ui.components.MySpinnerDropdown
import com.mertoenjosh.questprovider.ui.components.TopAppBar
import com.mertoenjosh.questprovider.ui.theme.QuestProviderTheme
import com.mertoenjosh.questprovider.util.InputWrapper
import timber.log.Timber


@Composable
fun AddQuestionScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(showBackIcon = true, title = R.string.add_question, onProfileOrBackIconClick = {
            navHostController.popBackStack()
        })
    }, content = { paddingValues ->
        AddQuestionScreenContent(
            modifier = Modifier.padding(paddingValues),
        )
    })
}

@Composable
fun AddQuestionScreenContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // Question
        MyOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = InputWrapper(),
            label = R.string.question,
            onValueChange = {}, // todo
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

        Row {
            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = InputWrapper(),
                label = R.string.correct_answer,
                onValueChange = {}, // todo
            )
        }

        // Wrong 1
        Row {
            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = InputWrapper(),
                label = R.string.wrong_choice_one,
                onValueChange = {}, // todo
            )
        }

        // Wrong 2
        Row {
            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = InputWrapper(),
                label = R.string.wrong_choice_two,
                onValueChange = {}, // todo
            )
        }

        // Wrong 3
        Row {
            MyOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = InputWrapper(),
                label = R.string.wrong_choice_three,
                onValueChange = {}, // todo
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
    showBackground = true, name = "Question details screen"
)
@Composable
fun AddQuestionScreenPreview() {
    QuestProviderTheme {
        QuestionDetailsScreen(navHostController = rememberNavController())
    }
}
