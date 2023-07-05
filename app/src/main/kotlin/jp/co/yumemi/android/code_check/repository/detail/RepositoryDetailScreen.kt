package jp.co.yumemi.android.code_check.repository.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import jp.co.yumemi.android.code_check.github.model.GithubRepoData
import jp.co.yumemi.android.code_check.github.model.exampleGithubRepoData
import jp.co.yumemi.android.code_check.theme.CodeCheckTheme

@Composable
fun RepositoryDetailScreen(
    repositoryName: String?,
    detailViewModel: RepositoryDetailViewModel = hiltViewModel(),
) {
    val repositoryUiState by detailViewModel.repositoryUiState.collectAsState()
    LaunchedEffect(repositoryName) {
        repositoryName?.let {
            detailViewModel.initRepository(repositoryName)
        }
    }

    Scaffold(
        topBar = { RepositoryDetailTopBar(repositoryName) }
    ) {
        val repository = repositoryUiState.repository
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
    repository: GithubRepoData,
) {
    val scrollState = rememberScrollState()
    Column(Modifier.verticalScroll(scrollState).padding(horizontal = 8.dp)) {
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
                text = if (repository.language != null) "Written in ${repository.language}" else "",
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
                repository = exampleGithubRepoData,
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

