package com.example.soe_than.splashy.ui.ui.NewPhotos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Context
import android.support.v7.preference.PreferenceManager
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.data.datasource.newsource.PhotoDataSource
import com.example.soe_than.splashy.ui.data.datasource.newsource.PhotoDataSourceFactory
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import com.example.soe_than.splashy.ui.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable


class NewViewModel(val context:Context):ViewModel() {

    var photoList: LiveData<PagedList<Photo>>
    var progress  = MutableLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 20

    private val sourceFactory: PhotoDataSourceFactory

    var themePref = MutableLiveData<String>()

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
    fun checkInternet():LiveData<Boolean>{

        var status = ConstantsUtils.checkConnectivity(context)
        if (status == true){
            progress.postValue(false)

        }else{
            progress.postValue(true)
        }
        return progress
    }

//    fun getThemeFromPref(): LiveData<String> {
//
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context /* Activity context */)
////        val name = sharedPreferences.getString("signature", "")
////        themePref.postValue(PreferencesUtils.getBoolean(context, "NIGHT_MODE", false))
//
//        themePref.postValue(sharedPreferences.getString("key_theme", ""))
//        return themePref
//
//
//    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}