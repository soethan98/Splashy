package com.example.soe_than.splashy.ui

import android.app.Application
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Build
import android.preference.PreferenceManager
import android.view.View
import com.example.soe_than.splashy.ui.data.PreferencesUtils
import com.example.soe_than.splashy.ui.ui.NewPhotos.NewViewModel


class App : Application() {

    val TAG = "App"
    private var isNightModeEnabled = false


    companion object {
        var _instance: App? = null

        fun getInstance(): App? {
            if (_instance == null) {
                _instance = App()
            }

            return _instance
        }
    }


    fun isNightModeEnabled(): Boolean {
        return isNightModeEnabled
    }

    fun setIsNightModeEnabled(isNightModeEnabled: Boolean) {
//        this.isNightModeEnabled = isNightModeEnabled
//         mPrefs.edit().putBoolean("NIGHT_MODE",isNightModeEnabled)
         PreferencesUtils.putBoolean(this,"NIGHT_MODE",isNightModeEnabled)
    }


    override fun onCreate() {
        super.onCreate()

//        mPrefs = PreferenceManager.getDefaultSharedPreferences(this)
//        this.isNightModeEnabled = mPrefs.getBoolean("NIGHT_MODE",false)
//        this.isNightModeEnabled = PreferencesUtils.getBoolean(this,"NIGHT_MODE",false)


    }
}