package com.example.soe_than.splashy.ui.data.datasource.collectionphoto

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.example.soe_than.splashy.ui.api.ApiService
import com.example.soe_than.splashy.ui.data.Vo.Collection
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.data.datasource.collection.CollectionDataSource
import com.example.soe_than.splashy.ui.data.datasource.newsource.PhotoDataSource
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import com.example.soe_than.splashy.ui.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable

class CollectionPhotoDataSource(val compositeDisposable: CompositeDisposable,val id:String) : PageKeyedDataSource<Int, Photo>() {

    private val apiService: ApiService by lazy { ApiService.create() }

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Photo>) {

        Log.i("Collection",id)
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        compositeDisposable.add(apiService.getaCollectionPhotos(id,ConstantsUtils.CLIENT_ID, 1, params.requestedLoadSize).subscribe({ photos: List<Photo> ->
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            callback.onResult(photos, null, 2)
        }, { t: Throwable ->
            val error = NetworkState.error(t.message)
            // publish the error
            networkState.postValue(error)
            initialLoad.postValue(error)
        }))

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {

        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(apiService.getaCollectionPhotos(id,ConstantsUtils.CLIENT_ID, params.key, params.requestedLoadSize).subscribe({ photos: List<Photo>? ->
            networkState.postValue(NetworkState.LOADED)
            val nextKey = (if (params.key === 4) null else params.key + 1)
            callback.onResult(photos!!, nextKey)
        }, { t: Throwable ->
            networkState.postValue(NetworkState.error(
                    t.message))
        }))

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {

    }
}