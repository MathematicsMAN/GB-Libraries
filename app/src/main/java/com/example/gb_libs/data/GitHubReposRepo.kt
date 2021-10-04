package com.example.gb_libs.data

import com.example.gb_libs.data.cache.RoomGithubRepositoriesCache
import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs.remote.ApiHolder
import com.example.gb_libs.utils.INetworkStatus

class GitHubReposRepo(
    private val networkStatus: INetworkStatus,
    val db: GitHubDatabase
) {

    fun getRepos(user: GitHubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        val cacheRepos = RoomGithubRepositoriesCache(db, user)

        if (isOnline) {
            ApiHolder.apiService.getRepos(user.reposUrl.orEmpty()).flatMap { repos ->
                cacheRepos.pushReposInDB(repos)
            }
        } else {
            cacheRepos.getReposFromDB()
        }
    }
}