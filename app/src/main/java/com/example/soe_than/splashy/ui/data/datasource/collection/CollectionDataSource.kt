package com.example.soe_than.splashy.ui.data.datasource.collection

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.example.soe_than.splashy.ui.api.ApiService
import com.example.soe_than.splashy.ui.data.Vo.Collection
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import com.example.soe_than.splashy.ui.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable

class CollectionDataSource(val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, Collection>() {

    private val apiService: ApiService by lazy { ApiService.create()}

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Collection>) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        compositeDisposable.add(apiService.getAllCollections(ConstantsUtils.CLIENT_ID, 1, params.requestedLoadSize).subscribe({ collections: List<Collection> ->
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            callback.onResult(collections, null, 2)
        }, { t: Throwable ->
            val error = NetworkState.error(t.message)
            // publish the error
            networkState.postValue(error)
            initialLoad.postValue(error)
        }))

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Collection>) {
        Log.i("NewFrag", "${params.key}")
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(apiService.getAllCollections(ConstantsUtils.CLIENT_ID, params.key, params.requestedLoadSize).subscribe({ collections: List<Collection>? ->
            networkState.postValue(NetworkState.LOADED)
            val nextKey = (if (params.key === 5) null else params.key + 1)
            callback.onResult(collections!!, nextKey)
        }, { t: Throwable ->
            networkState.postValue(NetworkState.error(
                    t.message))
        }))

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Collection>) {

    }
}