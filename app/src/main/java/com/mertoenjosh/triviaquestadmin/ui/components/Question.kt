package com.mertoenjosh.triviaquestadmin.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertoenjosh.triviaquestadmin.data.local.mockQuestions
import com.mertoenjosh.triviaquestadmin.data.models.TriviaQuestion
import com.mertoenjosh.triviaquestadmin.data.models.formatCategory
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme

@Composable
fun Question(
    question: TriviaQuestion,
    onQuestionClick: (TriviaQuestion) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onQuestionClick(question) },
        elevation = 10.dp
    ) {
        Column (
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = question.question,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ){
                Text(
                    text = "Easy",
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                )

                Text(
                    text = question.formatCategory(),
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                )

                Text(
                    text = question.author,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun QuestionPreview() {
    TriviaQuestAdminTheme {
        Question(question = mockQuestions[1], onQuestionClick = {})
    }
}