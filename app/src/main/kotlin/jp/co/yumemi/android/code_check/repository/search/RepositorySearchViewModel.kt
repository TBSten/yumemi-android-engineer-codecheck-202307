package jp.co.yumemi.android.code_check.repository.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.android.code_check.github.model.GithubRepoData
import jp.co.yumemi.android.code_check.github.model.exampleGithubRepoData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RepositorySearchViewModel @Inject constructor() : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _repositoriesUiState = MutableStateFlow(
        RepositoriesUiState(
            repositories = List(10) { exampleGithubRepoData },
        )
    )
    val repositoriesUiState = _repositoriesUiState.asStateFlow()

    fun updateSearchQuery(searchQuery: String) {
        _searchQuery.update { searchQuery }
    }
}

data class RepositoriesUiState(
    val repositories: List<GithubRepoData>,
)
