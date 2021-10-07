package com.example.gb_libs.data.cache

import com.example.gb_libs.data.GitHubRepo
import com.example.gb_libs.data.GitHubUser
import com.example.gb_libs.data.db.GitHubDatabase
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRoomGithubRepositoriesCache {
    fun pushReposInDB(user: GitHubUser, repos: List<GitHubRepo>): Completable
    fun getReposFromDB(user: GitHubUser): Single<List<GitHubRepo>>
}