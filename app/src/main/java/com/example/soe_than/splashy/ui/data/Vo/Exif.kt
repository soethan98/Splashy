package com.example.soe_than.splashy.ui.data.Vo

import com.google.gson.annotations.SerializedName

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
        var iso: Int = 0)

