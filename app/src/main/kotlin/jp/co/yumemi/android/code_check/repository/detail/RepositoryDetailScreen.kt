package jp.co.yumemi.android.code_check.repository.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import jp.co.yumemi.android.code_check.components.Chip
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
        val isLoading = repositoryUiState.isLoading
        Box(Modifier.padding(it)) {
            if (isLoading) {
                RepositoryLoading()
            } else if (repository == null) {
                RepositoryNotFound()
            } else {
                RepositoryDetailContent(
                    repository = repository,
                )
            }
        }
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RepositoryDetailContent(
    repository: GithubRepoData,
) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncImage(
                modifier = Modifier.size(150.dp),
                model = repository.ownerIconUrl,
                contentDescription = null,
                contentScale = ContentScale.Inside,
            )
            Column(Modifier.weight(1f)) {
                Text(
                    text = repository.name,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                )
                FlowRow {
                    Chip {
                        if (repository.language !== null) Text("Written by ${repository.language}")
                    }
                }
            }
        }

        // TODO RepoCircle
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

@Composable
private fun RepositoryLoading() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
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

