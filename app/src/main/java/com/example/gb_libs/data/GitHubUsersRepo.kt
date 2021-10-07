package com.example.gb_libs.data

import com.example.gb_libs.data.cache.RoomGithubUsersCache
import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs.remote.IApiHolder
import com.example.gb_libs.utils.INetworkStatus

class GitHubUsersRepo(
    private val networkStatus: INetworkStatus,
    private val db: GitHubDatabase,
    private val apiHolder: IApiHolder
) {

    fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        val cacheUsers = RoomGithubUsersCache(db)

        if (isOnline) {
            apiHolder.apiService.getUsers()
                .flatMap { users ->
                    cacheUsers.pushUsersInDB(users)
                }
        } else {
            cacheUsers.getUsersFromDB()
        }
    }
}