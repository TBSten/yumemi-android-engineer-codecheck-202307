package jp.co.yumemi.android.code_check.repository.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import jp.co.yumemi.android.code_check.github.model.GithubRepository
import jp.co.yumemi.android.code_check.github.model.exampleGithubRepository
import jp.co.yumemi.android.code_check.theme.CodeCheckTheme

@Composable
fun RepositoryDetailScreen(
    repositoryName: String?,
) {
    val repository: jp.co.yumemi.android.code_check.github.model.GithubRepository? =
        jp.co.yumemi.android.code_check.github.model.exampleGithubRepository

    Scaffold(
        topBar = { RepositoryDetailTopBar(repositoryName) }
    ) {
        Box(Modifier.padding(it)) {
            if (repository == null) {
                RepositoryNotFound()
            } else {
                RepositoryDetailContent(
                    repository = repository,
                )
            }
        }
    }

}

@Composable
fun RepositoryDetailContent(
    repository: jp.co.yumemi.android.code_check.github.model.GithubRepository,
) {
    Column(Modifier.padding(horizontal = 8.dp)) {
        val imageModifier =
            Modifier
                .padding(8.dp)
                .size(250.dp)
                .align(Alignment.CenterHorizontally)

        AsyncImage(
            model = repository.ownerIconUrl,
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = imageModifier,
        )

        Text(
            text = repository.name,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 16.dp),
        )

        Row {
            Text(
                text = "Written in ${repository.language}",
                modifier = Modifier.weight(1f),
            )
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                Text("${repository.stargazersCount} stars")
                Text("${repository.watchersCount} watchers")
                Text("${repository.forksCount} forks")
                Text("${repository.openIssuesCount} open issues")
            }
        }
    }
}

@Composable
private fun RepositoryDetailTopBar(repositoryName: String?) {
    TopAppBar(
        title = { Text(repositoryName ?: "レポジトリの詳細") },
    )
}

@Composable
private fun RepositoryNotFound() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("レポジトリが見つかりませんでした...")
    }
}

@Preview
@Composable
fun RepositoryDetailContentPreview() {
    CodeCheckTheme {
        Surface {
            RepositoryDetailContent(
                repository = jp.co.yumemi.android.code_check.github.model.exampleGithubRepository,
            )
        }
    }
}

@Preview
@Composable
fun RepositoryDetailNotFoundPreview() {
    CodeCheckTheme {
        Surface {
            RepositoryNotFound()
        }
    }
}

