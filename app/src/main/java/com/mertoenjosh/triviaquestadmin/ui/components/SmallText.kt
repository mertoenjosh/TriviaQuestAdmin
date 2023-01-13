package com.mertoenjosh.triviaquestadmin.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertoenjosh.triviaquestadmin.R
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme

@Composable
fun SmallText(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    link: Boolean = false,
    color: Color = Color.DarkGray
) {
    Text(
        text = stringResource(text),
        style = MaterialTheme.typography.body2.copy(
            fontWeight = FontWeight.Light,
            color = if (link) MaterialTheme.colors.primary else color
        ),
        modifier = modifier
    )
}

@Preview
@Composable
fun SmallTextPreview() {
    TriviaQuestAdminTheme {
        SmallText(text = R.string.dont_have_an_account)
    }
}