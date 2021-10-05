package com.example.gb_libs.di.modules

import android.content.Context
import com.example.gb_libs.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    fun app(): Context {
        return app
    }
}