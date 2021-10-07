package com.example.gb_libs.di.modules

import com.example.gb_libs.data.GitHubUsersRepo
import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs.di.scopes.UserScope
import com.example.gb_libs.remote.IApiHolder
import com.example.gb_libs.utils.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
open class UserModule {
    @Provides
    @UserScope
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
}