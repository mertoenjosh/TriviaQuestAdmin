package com.mertoenjosh.questprovider.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertoenjosh.questprovider.domain.models.Question
import com.mertoenjosh.questprovider.domain.models.formatCategory
import com.mertoenjosh.questprovider.ui.theme.EasyGreen
import com.mertoenjosh.questprovider.ui.theme.HardRed
import com.mertoenjosh.questprovider.ui.theme.MediumOrange
import com.mertoenjosh.questprovider.ui.theme.QuestProviderTheme

@Composable
fun Question(
    question: Question, onQuestionClick: (Question) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onQuestionClick(question) },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.background(
                    if (question.difficulty.lowercase() == "easy") {
                        EasyGreen
                    } else if (question.difficulty.lowercase() == "medium") {
                        MediumOrange
                    } else {
                        HardRed
                    }
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .background(
                        color = MaterialTheme.colors.surface
                    )
                    .padding(8.dp)
            ) {
                Text(
                    text = question.question,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onSurface
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(
                        text = question.difficulty,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onSurface
                    )

                    Text(
                        text = question.formatCategory(),
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onSurface
                    )

                    Text(
                        text = question.author,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
        }

    }
}

@Preview(
    name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, widthDp = 320
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun QuestionPreview() {
    val question = Question(
        id = "2929292",
        choices = listOf(
            "Bill Gates", "Mark Zucherburg", "Jeff Bezos"
        ),
        tags = listOf("general_knowledge"),
        category = "general_knowledge",
        question = "Who is the richest man on earth (2022)?",
        correctAnswer = "Elon Musk",
        difficulty = "easy",
        author = "mertoenjosh"
    )

    QuestProviderTheme {
        Question(question = question, onQuestionClick = {})
    }
}