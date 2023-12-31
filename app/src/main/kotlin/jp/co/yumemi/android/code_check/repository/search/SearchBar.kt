package jp.co.yumemi.android.code_check.repository.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.co.yumemi.android.code_check.theme.CodeCheckTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
) {
    var hasFocus by remember { mutableStateOf(false) }

    val textColor = MaterialTheme.colors.onSurface

    val shape = MaterialTheme.shapes.large

    CompositionLocalProvider(
        LocalContentColor provides MaterialTheme.colors.onSurface,
    ) {
        Row(
            modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            Row(
                modifier = Modifier
                    .shadow(12.dp, shape = shape)
                    .background(MaterialTheme.colors.surface, shape = shape)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "search",
                    modifier = Modifier.alpha(0.8f).padding(horizontal = 8.dp),
                )
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.weight(1f).onFocusChanged { hasFocus = it.hasFocus },
                    textStyle = TextStyle.Default.copy(color = textColor),
                    singleLine = true,
                    cursorBrush = SolidColor(textColor),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search,
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = { onSearch() },
                    ),
                )
                IconButton(
                    modifier = Modifier.alpha(if (hasFocus) 0.8f else 0f),
                    onClick = { onValueChange("") },
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "clear",
                    )
                }
            }

            IconButton(
                modifier = Modifier.shadow(12.dp, shape = shape)
                    .background(MaterialTheme.colors.surface, shape = shape),
                onClick = { onSearch() },
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "search repositories",
                )
            }

        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    CodeCheckTheme {
        SearchBar(
            value = "preview search bar",
            onValueChange = {},
            onSearch = {},
        )
    }
}
