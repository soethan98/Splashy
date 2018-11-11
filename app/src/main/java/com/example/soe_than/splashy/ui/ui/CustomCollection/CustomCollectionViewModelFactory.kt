package com.example.soe_than.splashy.ui.ui.CustomCollection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class CustomCollectionViewModelFactory(var id: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return CustomCollectionViewModel(id) as T
    }


    companion object {
        fun provideCollectionViewModelFactory(id:String):CustomCollectionViewModelFactory{
            return CustomCollectionViewModelFactory(id)
        }
    }
}