package com.mertoenjosh.questprovider.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mertoenjosh.questprovider.R
import com.mertoenjosh.questprovider.ui.theme.QuestProviderTheme

@Composable
fun ProfileIcon(
    modifier: Modifier = Modifier,
    imageIcon: ImageVector,
    imageColorFilter: ColorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
    @StringRes contentDescription: Int = R.string.account_profile_picture,
    size: Dp,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
    ) {
        Image(
            imageVector = imageIcon,
            contentDescription = stringResource(contentDescription),
            colorFilter = imageColorFilter,
            modifier = Modifier.fillMaxSize()
        )

    }
}

@Preview
@Composable
fun ProfileIconPreview() {
    QuestProviderTheme {
        ProfileIcon(
            imageIcon = Icons.Default.AccountCircle, size = 56.dp
        )
    }
}