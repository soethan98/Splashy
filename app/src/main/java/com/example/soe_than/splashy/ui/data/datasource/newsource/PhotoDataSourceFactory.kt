package com.example.soe_than.splashy.ui.data.datasource.newsource

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.data.datasource.newsource.PhotoDataSource
import io.reactivex.disposables.CompositeDisposable

class PhotoDataSourceFactory(val compositeDisposable: CompositeDisposable): DataSource.Factory<Int, Photo>() {

    lateinit var photoDataSource: PhotoDataSource
    var mutableLiveData:MutableLiveData<PhotoDataSource>

    init {
        mutableLiveData = MutableLiveData<PhotoDataSource>()
    }

    override fun create(): DataSource<Int, Photo> {

        photoDataSource = PhotoDataSource(compositeDisposable)
        mutableLiveData.postValue(photoDataSource)
        return photoDataSource
    }

    fun getMutableLiveData(): LiveData<PhotoDataSource> {
        return mutableLiveData
    }
}