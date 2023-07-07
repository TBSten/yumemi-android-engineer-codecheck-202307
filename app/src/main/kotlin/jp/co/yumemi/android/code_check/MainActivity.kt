/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.co.yumemi.android.code_check.repository.detail.RepositoryDetailScreen
import jp.co.yumemi.android.code_check.repository.search.RepositorySearchScreen
import jp.co.yumemi.android.code_check.theme.CodeCheckTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppRoot() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = "repository/search",
    ) {
        composable("repository/search") {
            RepositorySearchScreen(
                gotoRepositoryDetailScreen = { navController.navigate("repository/detail?repositoryName=${it.name}") },
            )
        }
        composable(
            route = "repository/detail?repositoryName={repositoryName}",
            arguments = listOf(navArgument("repositoryName") { type = NavType.StringType }),
            enterTransition = { NavTransitions.RepoDetail.enter },
            exitTransition = { NavTransitions.RepoDetail.exit },
        ) {
            val repositoryName = it.arguments?.getString("repositoryName")
            RepositoryDetailScreen(
                repositoryName = repositoryName,
            )
        }
    }
}

private object NavTransitions {

    object RepoDetail {
        val enter =
            slideInHorizontally(tween(500)) { it } +
                    fadeIn(tween(400))
        val exit =
            slideOutHorizontally(tween(300)) { it } +
                    fadeOut(tween(200))
    }
}
