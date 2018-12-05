package com.example.soe_than.splashy.ui.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object PreferencesUtils {
    var PREFERENCE_NAME = "Splashy"





    fun removeValue(context: Context, key: String) {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        settings.edit().remove(key).apply()
    }


    fun getString(context: Context, key: String, defaultValue: String?): String {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return settings.getString(key, defaultValue)
    }

//    fun putString(context: Context, key: String, value: String): Boolean {
//        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
//        val editor = settings.edit()
//        editor.putString(key, value)
//        return editor.commit()
//    }


    fun putBoolean(context: Context,key: String,value:Boolean){


        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putBoolean(key, value)
          editor.commit()
    }


    fun getBoolean(context: Context, key: String): Boolean {
        return getBoolean(context, key, false)
    }


    fun getBoolean(context: Context, key: String, defaultValue: Boolean): Boolean {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return settings.getBoolean(key, defaultValue)
    }








}