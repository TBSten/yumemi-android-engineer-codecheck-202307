package jp.co.yumemi.android.code_check.repository.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.android.code_check.github.model.GithubRepoData
import jp.co.yumemi.android.code_check.github.model.GithubRepoDataRepository
import jp.co.yumemi.android.code_check.github.model.exampleGithubRepoData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailViewModel @Inject constructor(
    private val repoDataRepository: GithubRepoDataRepository,
) : ViewModel() {
    private val _repositoryUiState = MutableStateFlow(
        RepositoryUiState(
            repository = exampleGithubRepoData
        )
    )
    val repositoryUiState = _repositoryUiState.asStateFlow()
    fun initRepository(repoName: String) {
        viewModelScope.launch {
            val repository = repoDataRepository.getRepoData(repoName)
            _repositoryUiState.update {
                it.copy(
                    repository = repository,
                )
            }
        }
    }
}

data class RepositoryUiState(
    val repository: GithubRepoData?,
)
