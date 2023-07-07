package jp.co.yumemi.android.code_check.repository.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.co.yumemi.android.code_check.components.ListItem
import jp.co.yumemi.android.code_check.components.ListItemTailNext
import jp.co.yumemi.android.code_check.github.model.GithubRepoData

@Composable
fun RepositoryList(
    repositories: List<GithubRepoData>,
    onClickRepository: (GithubRepoData) -> Unit,
) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(repositories) { repository ->
            ListItem(
                onClick = { onClickRepository(repository) },
                content = { Text(repository.name) },
                tail = { ListItemTailNext() },
            )
        }
    }
}
