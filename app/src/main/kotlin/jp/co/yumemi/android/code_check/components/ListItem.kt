package jp.co.yumemi.android.code_check.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.co.yumemi.android.code_check.theme.CodeCheckTheme

@Composable
fun ListItem(
    head: (@Composable () -> Unit)? = null,
    tail: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val bgColor = MaterialTheme.colors.surface
    val contentColor = MaterialTheme.colors.onSurface

    Column(
        Modifier
            .background(bgColor)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            CompositionLocalProvider(
                LocalTextStyle provides LocalTextStyle.current.copy(color = contentColor),
                LocalContentColor provides contentColor,
            ) {
                if (head !== null) head()
                Box(Modifier.weight(1f)) {
                    content()
                }
                if (tail !== null) tail()
            }
        }
        Divider(color = MaterialTheme.colors.background)
    }
}

@Composable
fun ListItemTailNext(
    contentDescription: String? = null,
) {
    Icon(
        Icons.Default.KeyboardArrowRight,
        contentDescription = contentDescription,
    )
}

@Preview
@Composable
fun ListItemPreview() {
    CodeCheckTheme {
        Column {
            ListItem {
                Text("list item")
            }
            ListItem {
                Text("list item with ${"very ".repeat(100)} text")
            }
            ListItem {
                Text("list item")
            }
        }
    }
}

@Preview
@Composable
fun ListItemPreviewWithTail() {
    CodeCheckTheme {
        ListItem(
            content = {
                Text("test")
            },
            tail = {
                ListItemTailNext()
            },
        )
    }
}
