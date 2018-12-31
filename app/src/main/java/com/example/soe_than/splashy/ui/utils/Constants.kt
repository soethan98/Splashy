package com.example.soe_than.splashy.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.soe_than.splashy.ui.data.Vo.Photo

object ConstantsUtils{
    var navItemIndex = 0
    val TAG_NEW = "new"
    val TAG_FEATURED = "featured"
    val TAG_COLLECTON = "collections"
    var CURRENT_TAG = TAG_NEW
    val CLIENT_ID = "0d48650f9f8714c909a5cec40c0fd1a881386d6c016acbe882ec3cc12a880997"
    val BASE_URL="https://api.unsplash.com/"

    val LOADING = "Loading"
    val LOADED = "Loaded"


    fun checkConnectivity(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo


        if (netInfo != null && netInfo.isConnectedOrConnecting) {
            return true
        } else {
            return false
        }
    }
}