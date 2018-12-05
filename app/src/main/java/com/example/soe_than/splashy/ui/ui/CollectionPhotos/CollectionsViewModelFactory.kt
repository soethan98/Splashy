package com.example.soe_than.splashy.ui.ui.CollectionPhotos

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.soe_than.splashy.ui.ui.FeaturedPhotos.FeaturedViewModel
import com.example.soe_than.splashy.ui.ui.FeaturedPhotos.FeaturedViewModelFactory

class CollectionsViewModelFactory(var context: Context): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return CollectionsViewModel(context) as T
    }


    companion object {
        fun provideCollectionsViewModelFactory(context: Context): CollectionsViewModelFactory {
            return CollectionsViewModelFactory(context)
        }
    }
}