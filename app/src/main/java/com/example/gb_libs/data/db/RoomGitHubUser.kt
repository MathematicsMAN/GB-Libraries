package com.example.gb_libs.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitHubUser(
    @PrimaryKey val id: String,
    val login: String,
    val avatar: String,
    val repoUrl: String,
)