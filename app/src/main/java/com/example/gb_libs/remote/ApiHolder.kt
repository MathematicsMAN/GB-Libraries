package com.example.gb_libs.remote

interface IApiHolder {
    val apiService: GitHubUsersService
}

class ApiHolder(
    override val apiService: GitHubUsersService
) : IApiHolder
