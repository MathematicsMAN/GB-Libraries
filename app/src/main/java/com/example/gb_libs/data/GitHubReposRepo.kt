package com.example.gb_libs.data

import com.example.gb_libs.data.cache.RoomGithubRepositoriesCache
import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs.remote.ApiHolder
import com.example.gb_libs.remote.IApiHolder
import com.example.gb_libs.utils.INetworkStatus

class GitHubReposRepo(
    private val networkStatus: INetworkStatus,
    val db: GitHubDatabase,
    private val apiHolder: IApiHolder
) {

    fun getRepos(user: GitHubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        val cacheRepos = RoomGithubRepositoriesCache(db, user)

        if (isOnline) {
            apiHolder.apiService.getRepos(user.reposUrl.orEmpty()).flatMap { repos ->
                cacheRepos.pushReposInDB(repos)
            }
        } else {
            cacheRepos.getReposFromDB()
        }
    }
}