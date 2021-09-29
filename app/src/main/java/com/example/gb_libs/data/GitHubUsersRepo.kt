package com.example.gb_libs.data

import com.example.gb_libs.remote.ApiHolder

class GitHubUsersRepo {

    fun getUsers() = ApiHolder.apiService.getUsers()
}