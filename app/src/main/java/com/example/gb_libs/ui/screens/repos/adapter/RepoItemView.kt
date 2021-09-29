package com.example.gb_libs.ui.screens.repos.adapter

import com.example.gb_libs.ui.items.IItemView

interface RepoItemView : IItemView {
    fun showRepo(repoName: String)
}