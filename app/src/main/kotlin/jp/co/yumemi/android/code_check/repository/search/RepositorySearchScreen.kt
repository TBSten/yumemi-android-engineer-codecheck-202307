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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.github.model.GithubRepository
import jp.co.yumemi.android.code_check.github.model.exampleGithubRepository
import jp.co.yumemi.android.code_check.theme.CodeCheckTheme

@Composable
fun RepositorySearchScreen() {
    // 検証用
    var text by remember { mutableStateOf("") }
    val repositories =
        remember { List(10) { jp.co.yumemi.android.code_check.github.model.exampleGithubRepository } }

    RepositorySearchContent(
        searchQuery = text,
        onChangeSearchQuery = { text = it },
        repositories = repositories,
        onClickRepository = { /* TODO goto detail page */ },
    )
}

@Composable
private fun RepositorySearchContent(
    searchQuery: String,
    onChangeSearchQuery: (String) -> Unit,
    repositories: List<jp.co.yumemi.android.code_check.github.model.GithubRepository>?,
    onClickRepository: (jp.co.yumemi.android.code_check.github.model.GithubRepository) -> Unit,
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
    @PreviewParameter(PreviewGithubRepositoryProvider::class) repositories: List<jp.co.yumemi.android.code_check.github.model.GithubRepository>?,
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

private class PreviewGithubRepositoryProvider :
    PreviewParameterProvider<List<jp.co.yumemi.android.code_check.github.model.GithubRepository>?> {
    override val values
        get() = sequenceOf(
            null,
            listOf(),
            listOf(jp.co.yumemi.android.code_check.github.model.exampleGithubRepository),
            List(100) { jp.co.yumemi.android.code_check.github.model.exampleGithubRepository },
        )
}
