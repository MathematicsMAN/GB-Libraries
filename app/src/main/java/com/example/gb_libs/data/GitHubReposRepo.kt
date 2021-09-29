package com.example.gb_libs.data

import com.example.gb_libs.remote.ApiHolder

class GitHubReposRepo {

    fun getRepos(urlRepos: String?) = ApiHolder.apiService.getRepos(urlRepos)
}