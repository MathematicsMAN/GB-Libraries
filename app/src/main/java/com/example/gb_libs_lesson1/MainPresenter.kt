package com.example.gb_libs_lesson1

class MainPresenter(
    private val view: MainView,
    private val model: CountersModel
) {

    fun counterClick(index: Int) {
        val nextValue = model.next(index)
        view.setButtonText(index, nextValue.toString())
    }
}