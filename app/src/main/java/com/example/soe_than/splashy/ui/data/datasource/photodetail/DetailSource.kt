package com.example.soe_than.splashy.ui.data.datasource.photodetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.example.soe_than.splashy.ui.api.ApiService
import com.example.soe_than.splashy.ui.data.Vo.PhotoDetail
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import io.reactivex.schedulers.Schedulers

class DetailSource() {
    var apiService: ApiService
    var photoDetail: MutableLiveData<PhotoDetail>


    init {
        apiService = ApiService.create()
        photoDetail = MutableLiveData()
    }

    companion object {
        var sInstance: DetailSource? = null
        val LOCK = Object()

        fun getInstance(): DetailSource? {
            Log.d("fesdfesrg", "Getting the repository")
            if (sInstance == null) {
                synchronized(LOCK) {

                    sInstance = DetailSource()
                    Log.d("fe", "Madegg new repository")

                }
            }
            return sInstance
        }


    }


    fun getPhotoDetails(photoId: String): LiveData<PhotoDetail> {
        Log.i("Detail","Here")

        apiService.getPhotoDetail(photoId,ConstantsUtils.CLIENT_ID)
                .subscribeOn(Schedulers.io()).toObservable()
                .subscribe({ it ->
                    Log.i("Detail",it.id)
                    photoDetail.postValue(it!!)
                }, { t: Throwable ->
                    Log.i("error: %s", t.message)
                })

        return photoDetail
    }

}