package com.example.soe_than.splashy.ui.ui.PhotoDetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.soe_than.splashy.ui.data.datasource.photodetail.DetailSource
import com.example.soe_than.splashy.ui.ui.NewPhotos.NewViewModelFactory

class DetailViewModelFactory(val context: Context, var id: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return DetailViewModel(context, id) as T
    }

    companion object {
        fun provideDetailViewModelFactory(context: Context,photoId:String): DetailViewModelFactory {
            return DetailViewModelFactory(context,photoId)
        }
    }
}