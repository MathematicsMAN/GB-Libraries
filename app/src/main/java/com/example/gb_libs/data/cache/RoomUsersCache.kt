package com.example.gb_libs.data.cache

import com.example.gb_libs.data.GitHubUser
import com.example.gb_libs.data.db.GitHubDatabase
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single

interface RoomUsersCache {
    fun pushUsersInDB(users: List<GitHubUser>): Single<List<GitHubUser>>
    fun getUsersFromDB(): Single<List<GitHubUser>>
}