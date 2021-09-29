package com.example.gb_libs.ui.images

interface IImageLoader<T> {
    fun loadTo(url: String, target: T)
}