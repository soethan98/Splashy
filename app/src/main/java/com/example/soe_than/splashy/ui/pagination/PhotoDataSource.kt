package com.example.soe_than.splashy.ui.pagination

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.example.soe_than.splashy.ui.api.ApiService
import com.example.soe_than.splashy.ui.data.Vo.Photo

class PhotoDataSource: PageKeyedDataSource<Int, Photo>() {

    var apiService: ApiService
    var networkState:MutableLiveData<String>
    var initialLoading:MutableLiveData<String>

    init {

        apiService = ApiService.create()
        initialLoading = MutableLiveData()
        networkState = MutableLiveData()

    }



    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Photo>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {

    }
}