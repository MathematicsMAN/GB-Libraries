package com.example.gb_libs.ui

import android.os.Parcelable
import androidx.fragment.app.Fragment


fun <T : Parcelable> Fragment.extractInitParams(): T {
    val initParams = requireArguments().getParcelable<T>(this::class.java.name)
    return initParams!!
}

fun <T : Parcelable> Fragment.initParams() = lazy<T> {
    extractInitParams()
} // Инициализация через делегат
