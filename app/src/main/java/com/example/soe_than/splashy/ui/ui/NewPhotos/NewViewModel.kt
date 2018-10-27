package com.example.soe_than.splashy.ui.ui.NewPhotos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.data.datasource.PhotoDataSource
import com.example.soe_than.splashy.ui.data.datasource.factory.PhotoDataSourceFactory
import com.example.soe_than.splashy.ui.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable
import android.text.method.TextKeyListener.clear



class NewViewModel:ViewModel() {

    var photoList: LiveData<PagedList<Photo>>

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 20

    private val sourceFactory: PhotoDataSourceFactory

    init {
        sourceFactory = PhotoDataSourceFactory(compositeDisposable)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setEnablePlaceholders(false)
                .build()
        photoList = LivePagedListBuilder<Int, Photo>(sourceFactory, config).build()

    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<PhotoDataSource, NetworkState>(
            sourceFactory.mutableLiveData, { it.networkState })


    fun getListLiveData(): LiveData<PagedList<Photo>> {
        return photoList
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}