package jp.co.yumemi.android.code_check.repository.search

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.github.model.GithubRepoData
import jp.co.yumemi.android.code_check.github.model.exampleGithubRepoData
import jp.co.yumemi.android.code_check.theme.CodeCheckTheme

@Composable
fun RepositorySearchScreen(
    searchViewModel: RepositorySearchViewModel = hiltViewModel(),
) {
    // 検証用
    val text by searchViewModel.searchQuery.collectAsState()
    val repositoriesUiState by searchViewModel.repositoriesUiState.collectAsState()

    RepositorySearchContent(
        searchQuery = text,
        onChangeSearchQuery = { searchViewModel.updateSearchQuery(it) },
        repositories = repositoriesUiState.repositories,
        onClickRepository = { /* TODO goto detail page */ },
    )
}

@Composable
private fun RepositorySearchContent(
    searchQuery: String,
    onChangeSearchQuery: (String) -> Unit,
    repositories: List<GithubRepoData>?,
    onClickRepository: (GithubRepoData) -> Unit,
) {
    Scaffold(
        topBar = { RepositorySearchTopBar() },
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Box(Modifier.padding(16.dp).fillMaxWidth()) {
                SearchBar(
                    value = searchQuery,
                    onValueChange = onChangeSearchQuery,
                )
            }
            if (repositories.isNullOrEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        if (repositories == null)
                            "レポジトリを検索してね！"
                        else
                            "レポジトリがありません..."
                    )
                }
            } else {
                RepositoryList(
                    repositories = repositories,
                    onClickRepository = onClickRepository,
                )
            }
        }

    }
}

@Composable
private fun RepositorySearchTopBar() {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
    )
}


@Preview(name = "dark", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "light")
annotation class LightDarkPreviews

@LightDarkPreviews
@Composable
fun RepositorySearchContentPreview(
    @PreviewParameter(PreviewGithubRepoDataProvider::class) repositories: List<GithubRepoData>?,
) {
    CodeCheckTheme {
        RepositorySearchContent(
            searchQuery = "kotlin",
            onChangeSearchQuery = {},
            repositories = repositories,
            onClickRepository = {},
        )
        Text("${isSystemInDarkTheme()}")
    }
}

private class PreviewGithubRepoDataProvider :
    PreviewParameterProvider<List<GithubRepoData>?> {
    override val values
        get() = sequenceOf(
            null,
            listOf(),
            listOf(exampleGithubRepoData),
            List(100) { exampleGithubRepoData },
        )
}
