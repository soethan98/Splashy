package com.example.soe_than.splashy.ui.ui.PhotoDetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.util.Log
import com.example.soe_than.splashy.ui.data.Vo.PhotoDetail
import com.example.soe_than.splashy.ui.data.datasource.photodetail.DetailSource

class DetailViewModel(val context: Context,val photoId:String):ViewModel(){

    fun getPhotoDetails(): LiveData<PhotoDetail> {
        Log.i("Details","${DetailSource.getInstance()!!.getPhotoDetails(photoId)}")
        return DetailSource.getInstance()!!.getPhotoDetails(photoId)
    }
}