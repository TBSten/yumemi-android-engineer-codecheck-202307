package jp.co.yumemi.android.code_check.repository

data class GithubRepository(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
)

val exampleGithubRepository = GithubRepository(
    name = "mock repository",
    ownerIconUrl = "...",
    language = "...",
    stargazersCount = -1,
    watchersCount = -1,
    forksCount = -1,
    openIssuesCount = -1,
)
