package com.example.gb_libs.data.cache

import com.example.gb_libs.data.GitHubUser
import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs.data.db.RoomGitHubUser
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache(private val db: GitHubDatabase) : RoomUsersCache {

    override fun pushUsersInDB(users: List<GitHubUser>): Single<List<GitHubUser>> =
        Single.fromCallable {
            val roomUsers = users.map { user ->
                RoomGitHubUser(
                    user.id.toString(),
                    user.login.orEmpty(),
                    user.avatarUrl.orEmpty(),
                    user.reposUrl.orEmpty()
                )
            }
            db.userDao.insert(roomUsers)
            users
        }

    override fun getUsersFromDB(): Single<List<GitHubUser>> =
        Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GitHubUser(
                    login = roomUser.login,
                    id = roomUser.id.toLong(),
                    avatarUrl = roomUser.avatar,
                    reposUrl = roomUser.repoUrl,
                )
            }
        }
}