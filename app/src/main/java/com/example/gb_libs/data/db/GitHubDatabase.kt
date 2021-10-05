package com.example.gb_libs.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RoomGitHubUser::class, RoomGitHubRepository::class],
    version = 1
)
abstract class GitHubDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
}