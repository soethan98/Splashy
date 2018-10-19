package com.example.soe_than.splashy.ui.Vo

import com.google.gson.annotations.SerializedName

class UserLinks(
        @SerializedName("self")
        val self:String,
        @SerializedName("html")
        val html:String,
        @SerializedName("photo")
        val photo:String,
        @SerializedName("likes")
        val likes:String
)

