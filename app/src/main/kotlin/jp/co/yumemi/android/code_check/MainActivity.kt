/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
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

    NavHost(navController = navController, startDestination = "") {
    }
}
