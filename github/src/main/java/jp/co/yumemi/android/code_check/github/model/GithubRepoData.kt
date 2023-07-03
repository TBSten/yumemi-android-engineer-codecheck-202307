package jp.co.yumemi.android.code_check.github.model

data class GithubRepoData(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
)

val exampleGithubRepoData = GithubRepoData(
    name = "mock repository",
    ownerIconUrl = "...",
    language = "...",
    stargazersCount = -1,
    watchersCount = -1,
    forksCount = -1,
    openIssuesCount = -1,
)
