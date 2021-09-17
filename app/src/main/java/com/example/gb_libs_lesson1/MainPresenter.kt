package com.example.gb_libs_lesson1

import moxy.MvpPresenter

class MainPresenter(
    private val model: CountersModel
) : MvpPresenter<MainView>() {

    fun counterClick(index: Int) {
        val nextValue = model.next(index)
        when (index) {
            0 -> viewState.setButton1Text(nextValue.toString())
            1 -> viewState.setButton2Text(nextValue.toString())
            2 -> viewState.setButton3Text(nextValue.toString())
        }
    }
}