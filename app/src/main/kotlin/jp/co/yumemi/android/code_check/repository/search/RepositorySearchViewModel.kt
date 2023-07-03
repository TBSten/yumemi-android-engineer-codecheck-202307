package jp.co.yumemi.android.code_check.repository.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.android.code_check.github.model.GithubRepoData
import jp.co.yumemi.android.code_check.github.model.GithubRepoDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositorySearchViewModel @Inject constructor(
    private val repoDataRepository: GithubRepoDataRepository,
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _repositoriesUiState = MutableStateFlow(
        RepositoriesUiState(
            isSearching = false,
            repositories = emptyList(),
        )
    )
    val repositoriesUiState = _repositoriesUiState.asStateFlow()

    fun updateSearchQuery(searchQuery: String) {
        _searchQuery.update { searchQuery }
    }

    fun searchRepository() {
        _repositoriesUiState.update { it.copy(isSearching = true) }
        viewModelScope.launch {
            val repositories = repoDataRepository.searchRepositories(searchQuery.value)
            _repositoriesUiState.update {
                it.copy(
                    isSearching = false,
                    repositories = repositories,
                )
            }
        }.invokeOnCompletion {
            _repositoriesUiState.update { it.copy(isSearching = false) }
        }
    }
}

data class RepositoriesUiState(
    val isSearching: Boolean,
    val repositories: List<GithubRepoData>,
)
