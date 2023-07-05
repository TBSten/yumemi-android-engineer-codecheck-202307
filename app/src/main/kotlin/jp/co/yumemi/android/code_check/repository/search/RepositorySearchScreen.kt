package jp.co.yumemi.android.code_check.repository.search

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
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
    gotoRepositoryDetailScreen: (GithubRepoData) -> Unit,
) {
    // 検証用
    val text by searchViewModel.searchQuery.collectAsState()
    val repositoriesUiState by searchViewModel.repositoriesUiState.collectAsState()

    RepositorySearchContent(
        searchQuery = text,
        onChangeSearchQuery = { searchViewModel.updateSearchQuery(it) },
        isLoadingRepositories = repositoriesUiState.isSearching,
        isError = repositoriesUiState.isError,
        repositories = repositoriesUiState.repositories,
        onSearchRepositories = { searchViewModel.searchRepository() },
        onClickRepository = { gotoRepositoryDetailScreen(it) },
    )
}

@Composable
private fun RepositorySearchContent(
    searchQuery: String,
    onChangeSearchQuery: (String) -> Unit,
    isLoadingRepositories: Boolean,
    isError: Boolean,
    repositories: List<GithubRepoData>?,
    onSearchRepositories: () -> Unit,
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
            if (isError) {
                Alert {
                    Text("エラーが発生しました...")
                }
            }
            Box(Modifier.padding(16.dp).fillMaxWidth()) {
                SearchBar(
                    value = searchQuery,
                    onValueChange = onChangeSearchQuery,
                    onSearch = { onSearchRepositories() },
                )
            }
            if (repositories.isNullOrEmpty()) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    when (repositories) {
                        null -> Text("レポジトリを検索してね！")
                        else -> Text("レポジトリがありません...")
                    }
                    if (isLoadingRepositories) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    if (isLoadingRepositories) {
                        CircularProgressIndicator()
                    } else {
                        RepositoryList(
                            repositories = repositories,
                            onClickRepository = onClickRepository,
                        )
                    }
                }
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
            isLoadingRepositories = false,
            isError = false,
            onSearchRepositories = {},
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
