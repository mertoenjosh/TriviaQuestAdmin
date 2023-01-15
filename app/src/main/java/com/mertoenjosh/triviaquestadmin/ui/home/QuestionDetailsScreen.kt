package com.mertoenjosh.triviaquestadmin.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertoenjosh.triviaquestadmin.R
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme
import com.mertoenjosh.triviaquestadmin.ui.components.TopAppBar

@Composable
fun QuestionDetailsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                showBackIcon = true,
                title = R.string.question_details,
                onProfileOrBackIconClick = {
                    // todo: Back clicked
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

    }
}

@Preview(
    showBackground = true,
    name = "Question details screen"
)
@Composable
fun QuestionDetailsScreenPreview() {
    TriviaQuestAdminTheme {
        QuestionDetailsScreen()
    }
}