package jp.co.yumemi.android.code_check.github.model

import com.squareup.moshi.Json
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubRepoService {
    @GET("search/repositories")
    @Headers("Accept: application/vnd.github.v3+json")
    suspend fun search(@Query("q") searchQuery: String): Response<SearchResponse>

    data class SearchResponse(
        val items: List<RepoData>,
    )

    data class RepoData(
        val name: String,
        val owner: Owner?,
        val language: String?,
        @field:Json(name = "stargazers_count")
        val stargazersCount: Long,
        @field:Json(name = "watchers_count")
        val watchersCount: Long,
        @field:Json(name = "forks_count")
        val forksCount: Long,
        @field:Json(name = "open_issues_count")
        val openIssuesCount: Long,
    ) {
        data class Owner(
            @field:Json(name = "avatar_url")
            val avatarUrl: String,
        )

        fun toGithubRepoData(): GithubRepoData {
            return GithubRepoData(
                name = this.name,
                ownerIconUrl = this.owner?.avatarUrl,
                language = this.language,
                stargazersCount = this.stargazersCount,
                watchersCount = this.watchersCount,
                forksCount = this.forksCount,
                openIssuesCount = this.openIssuesCount,
            )

        }
    }
}
