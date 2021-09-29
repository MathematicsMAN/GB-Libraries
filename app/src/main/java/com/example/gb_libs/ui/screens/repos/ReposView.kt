package com.example.gb_libs.ui.screens.repos

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ReposView : MvpView {
    fun  init()
    fun updateList()
}