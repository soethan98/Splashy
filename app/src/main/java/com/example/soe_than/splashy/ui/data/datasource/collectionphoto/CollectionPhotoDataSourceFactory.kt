package com.example.soe_than.splashy.ui.data.datasource.collectionphoto

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.soe_than.splashy.ui.data.Vo.Photo
import io.reactivex.disposables.CompositeDisposable

class CollectionPhotoDataSourceFactory(val compositeDisposable: CompositeDisposable,val id:String): DataSource.Factory<Int, Photo>() {

    lateinit var photoCollection: CollectionPhotoDataSource
    var mutableLiveData: MutableLiveData<CollectionPhotoDataSource>

    init {
        mutableLiveData = MutableLiveData()
    }

    override fun create(): DataSource<Int, Photo> {

        photoCollection = CollectionPhotoDataSource(compositeDisposable,id)
        mutableLiveData.postValue(photoCollection)
        return photoCollection
    }

    fun getMutableLiveData(): LiveData<CollectionPhotoDataSource> {
        return mutableLiveData
    }
}
