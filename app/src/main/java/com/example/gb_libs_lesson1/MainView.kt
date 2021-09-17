package com.example.gb_libs_lesson1

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainView: MvpView {

    fun setButton1Text(text: String)
    fun setButton2Text(text: String)
    fun setButton3Text(text: String)
}