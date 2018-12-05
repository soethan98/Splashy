package com.example.soe_than.splashy.ui.ui.FeaturedPhotos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Context
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.data.datasource.feature.FeatureDataSource
import com.example.soe_than.splashy.ui.data.datasource.feature.FeatureDataSourceFactory
import com.example.soe_than.splashy.ui.data.datasource.newsource.PhotoDataSource
import com.example.soe_than.splashy.ui.data.datasource.newsource.PhotoDataSourceFactory
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import com.example.soe_than.splashy.ui.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable

class FeaturedViewModel(var context:Context): ViewModel() {

    var photoList: LiveData<PagedList<Photo>>
    var progress = MutableLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 20

    private val sourceFactory: FeatureDataSourceFactory

    init {
        sourceFactory = FeatureDataSourceFactory(compositeDisposable)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setEnablePlaceholders(false)
                .build()
        photoList = LivePagedListBuilder<Int, Photo>(sourceFactory, config).build()

    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<FeatureDataSource, NetworkState>(
            sourceFactory.mutableLiveData, { it.networkState })


    fun getListLiveData(): LiveData<PagedList<Photo>> {
        return photoList
    }

    fun checkInternet():LiveData<Boolean>{

        var status = ConstantsUtils.checkConnectivity(context)
        if (status == true){
            progress.postValue(false)

        }else{
            progress.postValue(true)
        }
        return progress
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}