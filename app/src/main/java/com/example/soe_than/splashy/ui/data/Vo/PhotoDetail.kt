package com.example.soe_than.splashy.ui.data.Vo

import com.google.gson.annotations.SerializedName

data class PhotoDetail(
        @SerializedName("id")
        val id: String,
        @SerializedName("created_at")
        val created_at: String,
        @SerializedName("width")
        val width: Int,
        @SerializedName("height")
        val height: Int,
        @SerializedName("color")
        val color: String,
        @SerializedName("likes")
        val likes: Int,
        @SerializedName("user")
        val user: User,
        @SerializedName("exif")
        val exif: Exif,
        @SerializedName("links")
        val links: Links,
        @SerializedName("urls")
        val urls: Urls,
        @SerializedName("views")
        val views:Int,
        @SerializedName("downloads")
        val downloads:Int

)
