package com.mertoenjosh.triviaquestadmin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp

@Composable
fun ProfileIcon(
    modifier: Modifier = Modifier,
    imageIcon: ImageVector,
    imageColorFilter: ColorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
    contentDescription: String,
    size: Dp,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
    ) {
        Image(
            imageVector = imageIcon,
            contentDescription = contentDescription,
            colorFilter = imageColorFilter,
            modifier = Modifier.fillMaxSize()
        )

    }
}