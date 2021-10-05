package com.example.gb_libs.di.modules

import com.example.gb_libs.data.GitHubReposRepo
import com.example.gb_libs.data.GitHubUsersRepo
import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs.remote.IApiHolder
import com.example.gb_libs.utils.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        networkStatus: INetworkStatus,
        db: GitHubDatabase,
        apiHolder: IApiHolder
    ): GitHubUsersRepo {
        return GitHubUsersRepo(
            networkStatus = networkStatus,
            db = db,
            apiHolder = apiHolder
        )
    }

    @Singleton
    @Provides
    fun reposRepo(
        networkStatus: INetworkStatus,
        db: GitHubDatabase,
        apiHolder: IApiHolder
    ): GitHubReposRepo {
        return GitHubReposRepo(
            networkStatus = networkStatus,
            db = db,
            apiHolder = apiHolder
        )
    }
}