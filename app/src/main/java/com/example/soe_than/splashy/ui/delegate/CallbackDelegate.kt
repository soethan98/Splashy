package com.example.soe_than.splashy.ui.delegate

import android.widget.CompoundButton

interface CallbackDelegate {

    fun getThemePref(checked: Boolean, switchView: CompoundButton)
}