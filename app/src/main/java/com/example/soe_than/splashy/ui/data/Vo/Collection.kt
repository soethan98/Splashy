package com.example.soe_than.splashy.ui.data.Vo

import com.google.gson.annotations.SerializedName


data class Collection(
        @SerializedName("id")
        val id: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("published_at")
        val published_at: String,
        @SerializedName("curated")
        val curated: Boolean,
        @SerializedName("total_photos")
        val total_photos: Int,
        @SerializedName("private")
        val privateX: Boolean,
        @SerializedName("share_key")
        val share_key: String,
        @SerializedName("cover_photo")
        val cover_photo: CoverPhoto,
        @SerializedName("user")
        val user: User,
        @SerializedName("links")
        val links: Links)





