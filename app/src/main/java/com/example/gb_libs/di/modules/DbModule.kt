package com.example.gb_libs.di.modules

import android.content.Context
import androidx.room.Room
import com.example.gb_libs.data.db.GitHubDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun db(context: Context): GitHubDatabase {
        return Room.databaseBuilder(
            context,
            GitHubDatabase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        private const val DB_NAME = "database.db"
    }
}