package com.mertoenjosh.questprovider.ui.components

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
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.theme.QuestProviderTheme

@Composable
fun TopAppBar(
    @StringRes title: Int,
    showBackIcon: Boolean = false,
    profileIcon: ImageVector? = null,
    onProfileOrBackIconClick: ()->Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colors.primarySurface)
    ) {
        if (showBackIcon) {
            MyIconButton(onIconClicked = onProfileOrBackIconClick )
        }

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
                .padding(start = 8.dp, end = 16.dp)
        )

        if (profileIcon != null) {
            ProfileIcon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { onProfileOrBackIconClick() }
                    .padding(10.dp),
                imageIcon = profileIcon,
                size = 30.dp,
            )
        } else {
            // todo: Use constraints to style
            Text(text = "", modifier = Modifier.padding(16.dp))
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun TopAppBarPreview() {
    QuestProviderTheme {
        TopAppBar(
            title = R.string.trivia_quest,
            profileIcon = Icons.Filled.AccountCircle,
            onProfileOrBackIconClick = { }
        )
    }
}