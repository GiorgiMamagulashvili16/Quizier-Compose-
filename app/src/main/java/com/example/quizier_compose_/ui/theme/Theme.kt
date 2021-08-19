package com.example.quizier_compose_.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.Yellow,
    primaryVariant = mainDarkBackground,
    onBackground = Color.White,
    surface = darkSurface,
    onSurface = Color.White,
    secondary = green,
    secondaryVariant = orange
)

private val LightColorPalette = lightColors(
    primary = Color.Blue,
    primaryVariant = mainLightBackground,
    onBackground = Color.Black,
    surface = lightSurface,
    onSurface = Color.Black,
    secondary = lightGreen,
    secondaryVariant = orange
)

@Composable
fun QuizierComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
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