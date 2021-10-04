package com.example.gb_libs.data.cache

import com.example.gb_libs.data.GitHubRepo
import com.example.gb_libs.data.GitHubUser
import com.example.gb_libs.data.db.GitHubDatabase
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single

interface RoomReposCache {
    fun pushReposInDB(repos: List<GitHubRepo>): Single<List<GitHubRepo>>
    fun getReposFromDB(): Single<List<GitHubRepo>>
}