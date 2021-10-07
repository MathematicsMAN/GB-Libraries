package com.example.gb_libs.di.modules

import com.example.gb_libs.data.GitHubReposRepo
import com.example.gb_libs.data.cache.IRoomGithubRepositoriesCache
import com.example.gb_libs.data.cache.RoomGithubRepositoriesCache
import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs.di.scopes.RepoScope
import com.example.gb_libs.remote.IApiHolder
import com.example.gb_libs.utils.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
open class RepoModule {

    @Provides
    @RepoScope
    fun reposCache(db: GitHubDatabase): IRoomGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(db)
    }

    @Provides
    @RepoScope
    fun reposRepo(
        networkStatus: INetworkStatus,
        cache: IRoomGithubRepositoriesCache,
        apiHolder: IApiHolder
    ): GitHubReposRepo {
        return GitHubReposRepo(
            networkStatus = networkStatus,
            cacheRepos = cache,
            apiHolder = apiHolder
        )
    }
}