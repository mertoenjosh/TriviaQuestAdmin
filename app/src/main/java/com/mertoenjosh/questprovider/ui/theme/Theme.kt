package com.mertoenjosh.questprovider.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Black,
    primaryVariant = BlackPale,
    secondary = Black,
    background = BlackPale,
    surface = Black,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White,
)

private val LightColorPalette = lightColors(
    primary = BluePrimary,
    primaryVariant = BlueVariant,
    secondary = BlueDark,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    onPrimary = White,
    onSecondary = White,
)

@Composable
fun QuestProviderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}