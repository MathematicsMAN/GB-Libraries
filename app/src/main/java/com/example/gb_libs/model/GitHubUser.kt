package com.example.gb_libs.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(val login: String): Parcelable
