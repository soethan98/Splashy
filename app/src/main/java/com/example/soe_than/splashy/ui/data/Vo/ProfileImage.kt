package com.example.soe_than.splashy.ui.data.Vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProfileImage(
        @SerializedName("small")
        val small: String,
        @SerializedName("medium")
        val medium: String,
        @SerializedName("large")
        val large: String):Parcelable








