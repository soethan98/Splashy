package com.example.soe_than.splashy.ui.data.Vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Links(
        @SerializedName("self")
        val self:String,
        @SerializedName("html")
        val html:String,
        @SerializedName("download")
        val download:String,
        @SerializedName("download_location")
        val download_location:String):Parcelable

