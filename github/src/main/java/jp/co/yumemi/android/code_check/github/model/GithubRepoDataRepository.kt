package jp.co.yumemi.android.code_check.github.model

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepoDataRepository @Inject constructor(
    private val githubRepoService: GithubRepoService,
) {
    init {
        Log.d("github-repo-data-repository", "init")
    }

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
        Log.d(
            "github-repo-data-repository",
            "get $repositoryName from ${repositoriesCache.joinToString(" , ") { it.name }}"
        )
        if (repoInCache != null) return repoInCache
        TODO("not implement, fetch repository by repositoryName")
    }

}
