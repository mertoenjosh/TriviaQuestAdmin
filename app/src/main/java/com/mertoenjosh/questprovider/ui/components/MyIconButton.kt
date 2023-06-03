package com.mertoenjosh.questprovider.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.mertoenjosh.questprovider.R

@Composable
fun MyIconButton(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    @StringRes contentDescription: Int = R.string.back_btn,
    onIconClicked: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onIconClicked
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(contentDescription),
            tint = color
        )
    }
}