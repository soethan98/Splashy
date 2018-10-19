package com.example.soe_than.splashy.ui.Vo

import android.telephony.mbms.StreamingServiceInfo
import com.google.gson.annotations.SerializedName

class User(
        @SerializedName("id")
        val id:String,
        @SerializedName("username")
        val username:String,
        @SerializedName("name")
        val name:String,
        @SerializedName("profile_image")
        val profile_image: ProfileImage,
        @SerializedName("")
        val links:Links)



