package jp.co.yumemi.android.code_check.github.model

import javax.inject.Inject

class GithubRepoDataRepository @Inject constructor(
    private val githubRepoService: GithubRepoService,
) {
    private var repositoriesCache = listOf<GithubRepoData>()
    suspend fun searchRepositories(searchQuery: String): List<GithubRepoData> {
        val repositories = githubRepoService.search(searchQuery)
            .body()?.items
            ?.map { it.toGithubRepoData() }
        return repositories?.also {
            cacheRepositories(it)
        } ?: emptyList()
    }

    private suspend fun cacheRepositories(repoData: List<GithubRepoData>) {
        repositoriesCache = repoData
    }

    suspend fun getRepoData(repositoryName: String): GithubRepoData? {
        val repoInCache = repositoriesCache.firstOrNull { it.name == repositoryName }
        if (repoInCache != null) return repoInCache
        TODO("not implement, fetch repository by repositoryName")
    }

}
