package com.example.gb_libs.data

import com.example.gb_libs.data.cache.RoomGithubUsersCache
import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs.remote.ApiHolder
import com.example.gb_libs.utils.INetworkStatus

class GitHubUsersRepo(
    private val networkStatus: INetworkStatus,
    val db: GitHubDatabase
) {

    fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        val cacheUsers = RoomGithubUsersCache(db)

        if (isOnline) {
            ApiHolder.apiService.getUsers()
                .flatMap { users ->
                    cacheUsers.pushUsersInDB(users)
                }
        } else {
            cacheUsers.getUsersFromDB()
        }
    }
}