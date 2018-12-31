package com.example.soe_than.splashy.ui.data.Vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
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
        @SerializedName("links")
        val links: Links,
        @SerializedName("urls")
        val urls: Urls):Parcelable
