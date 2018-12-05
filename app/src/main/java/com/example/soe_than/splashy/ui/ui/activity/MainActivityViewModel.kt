package com.example.soe_than.splashy.ui.ui.activity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v7.app.AppCompatDelegate
import com.example.soe_than.splashy.ui.data.PreferencesUtils
import io.reactivex.Completable

class MainActivityViewModel(var context: Context) : ViewModel() {

    var themePref = MutableLiveData<Boolean>()


    fun changeTheme(checked: Boolean): Completable {
        return Completable.fromAction {
            when (checked) {
                true -> PreferencesUtils.putBoolean(context, "NIGHT_MODE", true)
                false -> PreferencesUtils.putBoolean(context, "NIGHT_MODE", false)
            }
        }

    }

    fun getThemeFromPref(): LiveData<Boolean> {
        themePref.postValue(PreferencesUtils.getBoolean(context, "NIGHT_MODE", false))
        return themePref


    }


}