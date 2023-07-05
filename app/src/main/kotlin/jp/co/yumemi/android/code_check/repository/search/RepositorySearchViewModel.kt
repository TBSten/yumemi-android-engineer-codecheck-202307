package jp.co.yumemi.android.code_check.repository.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.android.code_check.github.model.GithubRepoData
import jp.co.yumemi.android.code_check.github.model.GithubRepoDataRepository
import kotlinx.coroutines.Dispatchers
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
            isError = false,
            repositories = null,
        )
    )
    val repositoriesUiState = _repositoriesUiState.asStateFlow()

    fun updateSearchQuery(searchQuery: String) {
        _searchQuery.update { searchQuery }
    }

    fun searchRepository() {
        _repositoriesUiState.update { it.copy(isSearching = true, isError = false) }
        viewModelScope
            .launch(Dispatchers.IO) {
                try {
                    val repositories = repoDataRepository.searchRepositories(searchQuery.value)
                    _repositoriesUiState.update {
                        it.copy(
                            isSearching = false,
                            isError = false,
                            repositories = repositories,
                        )
                    }
                } catch (e: Exception) {
                    Log.e("error", "error catch : $e")
                    _repositoriesUiState.update {
                        it.copy(
                            isSearching = false,
                            isError = true,
                        )
                    }
                }
            }
    }
}

data class RepositoriesUiState(
    val isSearching: Boolean,
    val isError: Boolean,
    val repositories: List<GithubRepoData>?,
)
