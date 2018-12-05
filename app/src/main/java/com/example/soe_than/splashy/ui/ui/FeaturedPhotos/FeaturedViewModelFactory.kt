package com.example.soe_than.splashy.ui.ui.FeaturedPhotos

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.soe_than.splashy.ui.ui.NewPhotos.NewViewModel
import com.example.soe_than.splashy.ui.ui.NewPhotos.NewViewModelFactory

class FeaturedViewModelFactory(var context:Context):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return FeaturedViewModel(context) as T
    }


    companion object {
        fun provideFeatureViewModelFactory(context: Context): FeaturedViewModelFactory {
            return FeaturedViewModelFactory(context)
        }
    }
}