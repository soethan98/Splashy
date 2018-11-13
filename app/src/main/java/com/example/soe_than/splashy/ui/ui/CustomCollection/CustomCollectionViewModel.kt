package com.example.soe_than.splashy.ui.ui.CustomCollection

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.data.datasource.collectionphoto.CollectionPhotoDataSource
import com.example.soe_than.splashy.ui.data.datasource.collectionphoto.CollectionPhotoDataSourceFactory
import com.example.soe_than.splashy.ui.data.datasource.feature.FeatureDataSource
import com.example.soe_than.splashy.ui.data.datasource.feature.FeatureDataSourceFactory
import com.example.soe_than.splashy.ui.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable

class CustomCollectionViewModel(id:String): ViewModel() {

    var photoList: LiveData<PagedList<Photo>>

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 20

    private val sourceFactory: CollectionPhotoDataSourceFactory

    init {
        sourceFactory = CollectionPhotoDataSourceFactory(compositeDisposable,id)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(10)
                .setEnablePlaceholders(false)
                .build()
        photoList = LivePagedListBuilder<Int, Photo>(sourceFactory, config).build()

    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<CollectionPhotoDataSource, NetworkState>(
            sourceFactory.mutableLiveData, { it.networkState })


    fun getListLiveData(): LiveData<PagedList<Photo>> {
        return photoList
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}