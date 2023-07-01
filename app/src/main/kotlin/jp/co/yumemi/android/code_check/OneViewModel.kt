/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.parcelize.Parcelize

/**
 * TwoFragment で使う
 */
class OneViewModel(
    val context: Context
) : ViewModel() {

    // 検索結果
    fun searchResults(inputText: String): List<item> = runBlocking {
        return@runBlocking GlobalScope.async {
            return@async listOf(
                item(
                    name = "mock repository",
                    ownerIconUrl = "...",
                    language = "...",
                    stargazersCount = -1,
                    watchersCount = -1,
                    forksCount = -1,
                    openIssuesCount = -1,
                ),
            )
        }.await()
    }
}

@Parcelize
data class item(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
) : Parcelable