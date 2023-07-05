package jp.co.yumemi.android.code_check.repository.detail

import android.util.Log
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
class RepositoryDetailViewModel @Inject constructor(
    private val repoDataRepository: GithubRepoDataRepository,
) : ViewModel() {
    private val _repositoryUiState = MutableStateFlow(
        RepositoryUiState.init
    )
    val repositoryUiState = _repositoryUiState.asStateFlow()
    fun initRepository(repoName: String) {
        viewModelScope.launch {
            try {
                _repositoryUiState.update { it.toLoading() }
                val repository = repoDataRepository.getRepoData(repoName)
                if (repository == null) {
                    _repositoryUiState.update { it.toError() }
                    return@launch
                }
                _repositoryUiState.update { it.toSuccess(repository) }
            } catch (e: Exception) {
                Log.e("error", "init repository", e)
                _repositoryUiState.update { it.toError() }
            }
        }
    }
}

data class RepositoryUiState(
    val repository: GithubRepoData?,
    val isLoading: Boolean,
    val isError: Boolean,
) {
    companion object {
        val init = RepositoryUiState(
            repository = null,
            isLoading = true,
            isError = false,
        )
    }

    fun toLoading() = this.copy(
        isLoading = true,
        isError = false,
    )

    fun toSuccess(repository: GithubRepoData) = this.copy(
        repository = repository,
        isLoading = false,
        isError = false,
    )

    fun toError() = this.copy(
        isLoading = false,
        isError = true,
    )
}
