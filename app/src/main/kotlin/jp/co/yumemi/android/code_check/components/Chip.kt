package jp.co.yumemi.android.code_check.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.co.yumemi.android.code_check.theme.CodeCheckTheme

data class ChipColors(
    val border: Color,
    val background: Color,
    val text: Color,
) {
    companion object {
        val primary: ChipColors
            @Composable
            get() = MaterialTheme.colors.let { c ->
                ChipColors(
                    border = c.primaryVariant,
                    background = c.primary,
                    text = c.onPrimary,
                )
            }
    }
}

@Composable
fun Chip(
    chipColors: ChipColors = ChipColors.primary,
    content: @Composable () -> Unit,
) {
    val shape = RoundedCornerShape(10000.dp)
    Row(
        Modifier
            .border(width = 3.dp, color = chipColors.border, shape = shape)
            .background(color = chipColors.background, shape = shape)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides chipColors.text,
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun ChipPreview() {
    CodeCheckTheme {
        Chip {
            Text("Chip Preview")
        }
    }
}
