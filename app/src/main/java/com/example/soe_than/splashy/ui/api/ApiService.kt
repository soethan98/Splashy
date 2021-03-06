package com.example.soe_than.splashy.ui.api

import com.example.soe_than.splashy.ui.data.Vo.Collection
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.data.Vo.PhotoDetail
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface ApiService {

    @GET("photos")
    fun getPhotos(@Query("client_id") apiKey: String,
                  @Query("page") page: Int, @Query("per_page") per_page: Int): Observable<List<Photo>>

    @GET("photos/curated")
    fun getCuratedPhotos(@Query("client_id") apiKey: String,
                         @Query("page") page: Int, @Query("per_page") per_page: Int): Observable<List<Photo>>

    @GET("collections")
    fun getAllCollections(@Query("client_id") apiKey: String,
                          @Query("page") page: Int, @Query("per_page") per_page: Int): Observable<List<Collection>>

    @GET("collections/{id}/photos")
    fun getaCollectionPhotos(@Path("id") collectionId: String, @Query("client_id") apiKey: String, @Query("page") page: Int, @Query("per_page") per_page: Int): Observable<List<Photo>>

    @GET("photos/{id}")
    fun getPhotoDetail(@Path("id") photoId: String,
                       @Query("client_id") apiKey: String ):Flowable<PhotoDetail>


    companion object Factory {

        fun create(): ApiService {
            var okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ConstantsUtils.BASE_URL)
                    .client(okHttpClient)
                    .build()

            return retrofit.create(ApiService::class.java)
        }

    }


}