package jp.co.yumemi.android.code_check.github

import jp.co.yumemi.android.code_check.github.model.GithubRepoDataRepository
import jp.co.yumemi.android.code_check.github.model.GithubRepoService
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response

class GithubRepoDataRepositoryTest {
    @Test
    fun `一覧を検索した後にその1要素を取得できる`() = runTest {
        val searchQuery = "test"

        val response = Response.success(
            GithubRepoService.SearchResponse(
                items = listOf(
                    GithubRepoService.RepoData(
                        name = "test/1",
                        owner = GithubRepoService.RepoData.Owner(
                            avatarUrl = "...",
                        ),
                        language = "...",
                        stargazersCount = 0,
                        watchersCount = 0,
                        forksCount = 0,
                        openIssuesCount = 0,
                    )
                ),
            )
        )
        val githubServiceMock = object : GithubRepoService {
            override suspend fun search(searchQuery: String): Response<GithubRepoService.SearchResponse> {
                return response
            }
        }
        val repoDataRepository = GithubRepoDataRepository(githubServiceMock)

        repoDataRepository.searchRepositories(searchQuery)
        repoDataRepository.getRepoData("test/1")
    }
}