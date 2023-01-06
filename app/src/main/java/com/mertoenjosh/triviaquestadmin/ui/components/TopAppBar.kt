package com.mertoenjosh.triviaquestadmin.ui.components

import com.mertoenjosh.triviaquestadmin.R
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertoenjosh.triviaquestadmin.theme.TriviaQuestAdminTheme

@Composable
fun TopAppBar(@StringRes title: Int, icon: ImageVector, onIconClick: ()->Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colors.primarySurface)
    ) {
        Text(
            text = stringResource(title),
            color = MaterialTheme.colors.onPrimary,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                letterSpacing = 0.15.sp
            ),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp, end = 16.dp)
        )

        ProfileIcon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable { onIconClick() }
                .padding(10.dp),
            imageIcon = icon,
            size = 30.dp,
        )
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun TopAppBarPreview() {
    TriviaQuestAdminTheme {
        TopAppBar(
            title = R.string.trivia_quest,
            icon = Icons.Filled.AccountCircle,
            onIconClick = { }
        )
    }
}