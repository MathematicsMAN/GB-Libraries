package com.example.gb_libs.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubRepo(
    @Expose
    val id: String? = null,

    @Expose
    val name: String? = null,

    @Expose
    @SerializedName("forks_count")
    val forksCount: Int? = null
) : Parcelable
