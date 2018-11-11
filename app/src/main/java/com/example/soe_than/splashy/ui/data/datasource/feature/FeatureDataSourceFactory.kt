package com.example.soe_than.splashy.ui.data.datasource.feature

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.data.datasource.newsource.PhotoDataSource
import io.reactivex.disposables.CompositeDisposable

class FeatureDataSourceFactory(val compositeDisposable: CompositeDisposable): DataSource.Factory<Int, Photo>() {

    lateinit var featureDataSource: FeatureDataSource
    var mutableLiveData: MutableLiveData<FeatureDataSource>

    init {
        mutableLiveData = MutableLiveData<FeatureDataSource>()
    }

    override fun create(): DataSource<Int, Photo> {

        featureDataSource = FeatureDataSource(compositeDisposable)
        mutableLiveData.postValue(featureDataSource)
        return featureDataSource
    }

    fun getMutableLiveData(): LiveData<FeatureDataSource> {
        return mutableLiveData
    }
}