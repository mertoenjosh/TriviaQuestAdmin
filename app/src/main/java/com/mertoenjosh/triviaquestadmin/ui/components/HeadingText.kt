package com.mertoenjosh.triviaquestadmin.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertoenjosh.triviaquestadmin.R
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme
import java.util.*

@Composable
fun HeadingText(@StringRes text: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = text).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                locale = Locale.getDefault()
            ) else it.toString()
        },
        style = MaterialTheme.typography.h3.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            fontSize = 45.sp
        ), // 48.sp
        modifier = modifier.padding(8.dp)
    )
}

@Preview
@Composable
fun HeadingTextPreview() {
    TriviaQuestAdminTheme {
        HeadingText(text = R.string.welcome)
    }
}