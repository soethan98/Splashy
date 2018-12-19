package com.example.soe_than.splashy.ui.ui.activity.setting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import com.example.soe_than.splashy.R

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_setting)
//        this.getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

       val  sharePref = PreferenceManager.getDefaultSharedPreferences(this)

        if (sharePref.getString("key_theme","") == "1"){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        }


        supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, SettingsFragment())
                .commit()
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.getItemId()
//        if (id == android.R.id.home) {
//            onBackPressed()
//        }
//
//        return super.onOptionsItemSelected(item)
//    }
}
