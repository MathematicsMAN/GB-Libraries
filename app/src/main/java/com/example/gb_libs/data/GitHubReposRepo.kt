package com.example.gb_libs.data

import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs.data.db.RoomGitHubRepository
import com.example.gb_libs.remote.ApiHolder
import com.example.gb_libs.utils.INetworkStatus
import io.reactivex.rxjava3.core.Single

class GitHubReposRepo(
    private val networkStatus: INetworkStatus,
    val db: GitHubDatabase
) {
    fun getRepos(user: GitHubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            ApiHolder.apiService.getRepos(user.reposUrl.orEmpty()).flatMap { repos ->
                Single.fromCallable {
                    val roomUser = db.userDao.getByLogin(user.login.orEmpty())
                        ?: error("Нет такого пользователя")
                    val roomRepos = repos.map {
                        RoomGitHubRepository(
                            id = it.id ?: "",
                            name = it.name ?: "",
                            forkCount = it.forksCount ?: 0,
                            userId = roomUser.id
                        )
                    }
                    db.repositoryDao.insert(roomRepos)
                    repos
                }
            }
        } else {
            Single.fromCallable {
                val roomUser = db.userDao.getByLogin(user.login.orEmpty())
                    ?: error("Нет такого пользователя в БД")
                db.repositoryDao.getByUserId(roomUser.id).map {
                    GitHubRepo(
                        id = it.id,
                        name = it.name,
                        forksCount = it.forkCount
                    )
                }
            }

        }
    }
}