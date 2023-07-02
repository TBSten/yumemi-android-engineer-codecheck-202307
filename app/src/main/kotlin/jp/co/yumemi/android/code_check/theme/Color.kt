package jp.co.yumemi.android.code_check.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFFBB86FC),
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC5),
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF6200EE),
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC5),
)

fun colors(isDarkTheme: Boolean) = if (isDarkTheme) DarkColorPalette else LightColorPalette
