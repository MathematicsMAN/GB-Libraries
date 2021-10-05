package com.example.gb_libs.di.modules

import android.content.Context
import com.example.gb_libs.remote.ApiHolder
import com.example.gb_libs.remote.GitHubUsersService
import com.example.gb_libs.remote.IApiHolder
import com.example.gb_libs.utils.AndroidNetworkStatus
import com.example.gb_libs.utils.INetworkStatus
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrl(): String = "https://api.github.com"

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Provides
    fun gitHubUsersService(
        gson: Gson,
        @Named("baseUrl") baseUrl: String
    ): GitHubUsersService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GitHubUsersService::class.java)
    }

    @Provides
    @Singleton
    fun apiHolder(apiService: GitHubUsersService): IApiHolder {
        return ApiHolder(apiService = apiService)
    }

    @Provides
    @Singleton
    fun networkStatus(context: Context): INetworkStatus =
        AndroidNetworkStatus(context)
}