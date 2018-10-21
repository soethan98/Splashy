package com.example.soe_than.splashy.ui.pagination

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.soe_than.splashy.ui.data.Vo.Photo

class PhotoDataSourceFactory: DataSource.Factory<Int, Photo>() {

    lateinit var photoDataSource: PhotoDataSource
    var mutableLiveData:MutableLiveData<PhotoDataSource>

    init {
        mutableLiveData = MutableLiveData<PhotoDataSource>()
    }

    override fun create(): DataSource<Int, Photo> {

        photoDataSource = PhotoDataSource()
        mutableLiveData.postValue(photoDataSource)
        return photoDataSource
    }

    fun getMutableLiveData(): LiveData<PhotoDataSource> {
        return mutableLiveData
    }
}