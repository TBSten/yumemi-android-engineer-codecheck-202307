/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import jp.co.yumemi.android.code_check.repository.detail.RepositoryDetailScreen
import jp.co.yumemi.android.code_check.repository.search.RepositorySearchScreen
import jp.co.yumemi.android.code_check.theme.CodeCheckTheme
import java.util.Date

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var lastSearchDate: Date
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CodeCheckTheme {
                Surface {
                    AppRoot()
                }
            }
        }

    }
}

@Composable
fun AppRoot() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "repository/detail?repositoryName=xxx"
    ) {
        composable("repository/search") {
            RepositorySearchScreen()
        }
        composable(
            route = "repository/detail?repositoryName={repositoryName}",
            arguments = listOf(navArgument("repositoryName") { type = NavType.StringType }),
        ) {
            val repositoryName = it.arguments?.getString("repositoryName")
            RepositoryDetailScreen(
                repositoryName = repositoryName,
            )
        }
    }
}
