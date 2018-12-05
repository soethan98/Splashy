package com.example.soe_than.splashy.ui.ui.activity.setting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, SettingsFragment())
                .commit()
    }
}
