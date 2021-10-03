package com.example.gb_libs.remote

import com.example.gb_libs.data.GitHubRepo
import com.example.gb_libs.data.GitHubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GitHubUsersService {

    @GET("/users")
    fun getUsers(): Single<List<GitHubUser>>

    @GET
    fun getRepos(@Url reposUrl: String?): Single<List<GitHubRepo>>
}