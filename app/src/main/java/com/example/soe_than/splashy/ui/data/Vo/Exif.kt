package com.example.soe_than.splashy.ui.data.Vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exif(
        @SerializedName("make")
        var make: String? = null,
        @SerializedName("model")
        var model: String? = null,
        @SerializedName("exposure_time")
        var exposure_time: String? = null,
        @SerializedName("aperture")
        var aperture: String? = null,
        @SerializedName("focal_lenght")
        var focal_length: String? = null,
        @SerializedName("iso")
        var iso: Int = 0):Parcelable

