package jp.co.yumemi.android.code_check.repository.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.co.yumemi.android.code_check.github.model.GithubRepoData

@Composable
fun RepositoryList(
    repositories: List<GithubRepoData>,
    onClickRepository: (GithubRepoData) -> Unit,
) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(repositories) { repository ->
            Column(Modifier.clickable { onClickRepository(repository) }) {
                Row(
                    Modifier
                        .padding(8.dp)
                ) {
                    Text(repository.name)
                }
                Divider()
            }
        }
    }
}
