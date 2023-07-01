package jp.co.yumemi.android.code_check.repository.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jp.co.yumemi.android.code_check.R

@Composable
fun RepositorySearchScreen() {
    RepositorySearchContent()
}

@Composable
private fun RepositorySearchContent() {
    Scaffold(
        topBar = { RepositorySearchTopBar() },
    ) {
        Column(Modifier.padding(it)) {
            Box(Modifier.padding(8.dp).fillMaxWidth()) {
                // TODO:SearchBar
            }
            // TODO:RepositoryList
        }
    }
}

@Composable
private fun RepositorySearchTopBar() {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
    )
}
