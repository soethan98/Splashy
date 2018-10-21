package com.example.soe_than.splashy.ui.data.Vo

import com.google.gson.annotations.SerializedName

data class CoverPhoto(
        @SerializedName("width")
        val width: Int,
        @SerializedName("height")
        val height: Int,
        @SerializedName("color")
        val color: String,
        @SerializedName("likes")
        val likes: Int,
        @SerializedName("liked_by_user")
        val liked_by_user: Boolean,
        @SerializedName("user")
        val user: User,
        @SerializedName("urls")
        val urls: Urls,
        @SerializedName("links")
        val links: Links)



