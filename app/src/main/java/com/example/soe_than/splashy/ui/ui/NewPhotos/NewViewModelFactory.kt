package com.example.soe_than.splashy.ui.ui.NewPhotos

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import com.example.soe_than.splashy.ui.ui.CustomCollection.CustomCollectionViewModel
import com.example.soe_than.splashy.ui.ui.CustomCollection.CustomCollectionViewModelFactory

class NewViewModelFactory(val context:Context): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return NewViewModel(context) as T
    }


    companion object {
        fun provideNewViewModelFactory(context: Context): NewViewModelFactory {
            return NewViewModelFactory(context)
        }
    }
}