package com.example.soe_than.splashy.ui.ui.CollectionPhotos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Context
import com.example.soe_than.splashy.ui.data.Vo.Collection
import com.example.soe_than.splashy.ui.data.datasource.collection.CollectionDataSource
import com.example.soe_than.splashy.ui.data.datasource.collection.CollectionDataSourceFactory
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import com.example.soe_than.splashy.ui.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable

class CollectionsViewModel(var context:Context):ViewModel() {

    var collectionList: LiveData<PagedList<Collection>>
    var progress = MutableLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 20

    private val sourceFactory: CollectionDataSourceFactory

    init {
        sourceFactory = CollectionDataSourceFactory(compositeDisposable)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setEnablePlaceholders(false)
                .build()
        collectionList = LivePagedListBuilder<Int, Collection>(sourceFactory, config).build()

    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<CollectionDataSource, NetworkState>(
            sourceFactory.mutableLiveData, { it.networkState })


    fun getListLiveData(): LiveData<PagedList<Collection>> {
        return collectionList
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