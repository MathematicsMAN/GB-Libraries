package com.example.gb_libs.model

class GitHubUsersRepo {
    private val users = listOf(
        GitHubUser("user1"),
        GitHubUser("user2"),
        GitHubUser("user3"),
        GitHubUser("user4"),
        GitHubUser("user5")
    )
    fun getUsers() = users
}