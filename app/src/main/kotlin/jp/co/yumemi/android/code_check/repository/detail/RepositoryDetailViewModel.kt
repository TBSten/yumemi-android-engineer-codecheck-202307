package jp.co.yumemi.android.code_check.repository.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.android.code_check.github.model.GithubRepoData
import jp.co.yumemi.android.code_check.github.model.exampleGithubRepoData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class RepositoryDetailViewModel : ViewModel() {
    private val _repositoryUiState = MutableStateFlow(
        RepositoryUiState(
            repository = exampleGithubRepoData
        )
    )
    val repositoryUiState = _repositoryUiState.asStateFlow()
}

class RepositoryUiState(
    val repository: GithubRepoData?,
)
