package com.example.gb_libs.data

import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs.data.db.RoomGitHubUser
import com.example.gb_libs.remote.ApiHolder
import com.example.gb_libs.utils.INetworkStatus
import io.reactivex.rxjava3.core.Single
import timber.log.Timber

class GitHubUsersRepo(
    private val networkStatus: INetworkStatus,
    val db: GitHubDatabase
) {

    fun getUsers() = networkStatus
        .isOnlineSingle()
        .flatMap { isOnline ->
        Timber.d("Получение пользователей")
        if (isOnline) {
            Timber.d("Доступ к интернету есть. Загрузка данных из интернета")
            ApiHolder.apiService.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        Timber.d("Считывание в потоке")
                        val roomUsers = users.map { user ->
                            RoomGitHubUser(
                                user.id.toString(),
                                user.login.orEmpty(),
                                user.avatarUrl.orEmpty(),
                                user.reposUrl.orEmpty(),
                                ""
                            )
                        }
                        db.userDao.insert(roomUsers)
                        Timber.d("Добавление пользователей в БД: ${roomUsers.size} пользователей")
                        users
                    }
                }
        } else {
            Timber.d("Доступа к интернету нет. Загрузка данных из БД")
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GitHubUser(
                        login = roomUser.login,
                        id = roomUser.id.toLong(),
                        avatarUrl = roomUser.avatar,
                        reposUrl = roomUser.repoUrl
                    )
                }
            }
        }
    }

}