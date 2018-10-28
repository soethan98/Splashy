package com.example.soe_than.splashy.ui.data.datasource.collection

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.soe_than.splashy.ui.data.Vo.Collection
import io.reactivex.disposables.CompositeDisposable

class CollectionDataSourceFactory(val compositeDisposable: CompositeDisposable): DataSource.Factory<Int, Collection>() {

    lateinit var collectionDataSource: CollectionDataSource
    var mutableLiveData: MutableLiveData<CollectionDataSource>

    init {
        mutableLiveData = MutableLiveData()
    }

    override fun create(): DataSource<Int, Collection> {

        collectionDataSource = CollectionDataSource(compositeDisposable)
        mutableLiveData.postValue(collectionDataSource)
        return collectionDataSource
    }

    fun getMutableLiveData(): LiveData<CollectionDataSource> {
        return mutableLiveData
    }
}