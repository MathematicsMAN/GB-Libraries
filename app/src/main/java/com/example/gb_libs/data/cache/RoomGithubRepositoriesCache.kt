package com.example.gb_libs.data.cache

import com.example.gb_libs.data.GitHubRepo
import com.example.gb_libs.data.GitHubUser
import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs.data.db.RoomGitHubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomGithubRepositoriesCache(val db: GitHubDatabase) : IRoomGithubRepositoriesCache {

    override fun pushReposInDB(
        user: GitHubUser,
        repos: List<GitHubRepo>
    ): Completable = Completable.fromAction {
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
    }

    override fun getReposFromDB(user: GitHubUser): Single<List<GitHubRepo>> =
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