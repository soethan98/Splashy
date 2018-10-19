package com.example.soe_than.splashy.ui.Vo

import com.google.gson.annotations.SerializedName

class Links(
        @SerializedName("self")
        val self:String,
        @SerializedName("html")
        val html:String,
        @SerializedName("download")
        val download:String,
        @SerializedName("download_location")
        val download_location:String)

