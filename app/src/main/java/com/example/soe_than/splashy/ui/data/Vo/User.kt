package com.example.soe_than.splashy.ui.data.Vo

import android.location.Location
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
        @SerializedName("id")
        val id: String,
        @SerializedName("username")
        val username: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("profile_image")
        val profile_image: ProfileImage,
        @SerializedName("links")
        val links: Links,
        @SerializedName("total_collections")
        val totalCollections: Int,
        @SerializedName("total_likes")
        val totalLikes: Int,
        @SerializedName("total_photos")
        val totalPhotos: Int,
        @SerializedName("location")
        val location: String):Parcelable



