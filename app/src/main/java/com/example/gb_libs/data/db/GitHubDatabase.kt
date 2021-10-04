package com.example.gb_libs.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [RoomGitHubUser::class, RoomGitHubRepository::class],
    version = 1
    )
abstract class GitHubDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {

        private val MIGRATION_1_2 = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE RoomGitHubUser ADD COLUMN url TEXT DEFAULT '' NOT NULL")
            }
        }

        private const val DB_NAME = "database.db"

        private var instance: GitHubDatabase? = null
        fun getInstance() = instance ?: throw IllegalStateException("База данных не инициализирована")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, GitHubDatabase::class.java, DB_NAME)
//                    .addMigrations(MIGRATION_1_2)
                    .build()
            }
        }
    }
}