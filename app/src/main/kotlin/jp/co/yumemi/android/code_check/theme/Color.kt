package jp.co.yumemi.android.code_check.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF4F7DF5),
    primaryVariant = Color(0xFF87A6F6),
    onPrimary = Color(0xFFF4F4F4),
    background = Color(0xFF54555C),
    surface = Color(0xFF7D7E87),
    onSurface = Color(0xFFFBFBFB),
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF4F7DF5),
    primaryVariant = Color(0xFF87A6F6),
    onPrimary = Color(0xFFF4F4F4),
    background = Color(0xFF54555C),
    surface = Color(0xFF7D7E87),
    onSurface = Color(0xFFFBFBFB),
)

fun colors(isDarkTheme: Boolean) = if (isDarkTheme) DarkColorPalette else LightColorPalette
