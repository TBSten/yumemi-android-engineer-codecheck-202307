package jp.co.yumemi.android.code_check.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CodeCheckTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = colors(darkTheme),
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
