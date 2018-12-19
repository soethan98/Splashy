package com.example.soe_than.splashy.ui.ui.activity.setting


import android.content.SharedPreferences
import android.os.Bundle
import android.support.v14.preference.PreferenceFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDelegate
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.soe_than.splashy.R

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PreferenceManager.getDefaultSharedPreferences(activity)

        Log.i("SettingFrag","onCreate")
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
//    }
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        if (key.equals("key_theme",true)){

            restartActivity()

////            val name = sharedPreferences!!.getString(key, "")
//            if (name.equals("1")){
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            }else{
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//
//            }
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }


    override fun onStop() {
        super.onStop()
        preferenceScreen.sharedPreferences
                .unregisterOnSharedPreferenceChangeListener(this)
    }


    override fun onStart() {
        super.onStart()
        preferenceScreen.sharedPreferences
                .registerOnSharedPreferenceChangeListener(this)
    }


    fun restartActivity() {

        val mIntent = activity!!.intent
        activity!!.finish()
        startActivity(mIntent)
    }





}
