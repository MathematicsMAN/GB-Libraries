package com.example.gb_libs.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitHubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGitHubRepository(
    @PrimaryKey val id: String,
    val name: String,
    val forkCount: Int,
    val userId: String
)
