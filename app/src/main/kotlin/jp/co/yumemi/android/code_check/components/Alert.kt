package jp.co.yumemi.android.code_check.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Alert(
    modifier: Modifier = Modifier,
    iconContentDescription: String? = null,
    text: @Composable () -> Unit,
) {
    val backgroundColor = MaterialTheme.colors.error
    val contentColor = MaterialTheme.colors.onError

    Row(
        modifier = modifier
            .padding(16.dp)
            .background(backgroundColor)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides LocalTextStyle.current.copy(color = contentColor),
            LocalContentColor provides contentColor,
        ) {
            Icon(
                Icons.Default.ErrorOutline,
                contentDescription = iconContentDescription,
                modifier = Modifier.padding(end = 12.dp),
            )
            text()
        }
    }
}
