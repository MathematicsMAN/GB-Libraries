package com.example.gb_libs.data

import com.example.gb_libs.data.cache.IRoomGithubRepositoriesCache
import com.example.gb_libs.remote.IApiHolder
import com.example.gb_libs.utils.INetworkStatus

class GitHubReposRepo(
    private val networkStatus: INetworkStatus,
    private val cacheRepos: IRoomGithubRepositoriesCache,
    private val apiHolder: IApiHolder
) {

    fun getRepos(user: GitHubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            apiHolder.apiService.getRepos(user.reposUrl.orEmpty())
                .flatMap { repos ->
                    cacheRepos
                        .pushReposInDB(user, repos)
                        .toSingleDefault(repos)
                }
        } else {
            cacheRepos.getReposFromDB(user)
        }
    }
}