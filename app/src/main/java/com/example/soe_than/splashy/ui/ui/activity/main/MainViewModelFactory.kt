package com.example.soe_than.splashy.ui.ui.activity.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context

class MainViewModelFactory(var context: Context):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return MainActivityViewModel(context) as T
    }


    companion object {
        fun provideMainViewModelFactory(context: Context): MainViewModelFactory {
            return MainViewModelFactory(context)
        }
    }
}