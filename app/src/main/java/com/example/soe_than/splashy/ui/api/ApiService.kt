package com.example.soe_than.splashy.ui.api

import android.support.annotation.TransitionRes
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import io.reactivex.Observable
import retrofit2.http.GET
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface ApiService {
//    @GET("movie/popular")
//    fun getPopularMovies(@Query("api_key") apiKey: String): Single<PopularResponse>
//
//    @GET("movie/top_rated")
//    fun getTopRatedMovies(@Query("api_key") apiKey: String): Single<TopRatedResponse>
//
//    @GET("movie/upcoming")
//    fun getUpComingMovies(@Query("api_key") apiKey: String): Single<UpcomingResponse>
//
//    @GET("movie/now_playing")
//    fun getNowShowingMovies(@Query("api_key") apiKey: String): Single<NowShowingResponse>
//
//
//    @GET("movie/{id}/videos")
//    fun getTrailers(@Path("id") id: Int, @Query("api_key") apiKey: String): Single<TrailerResponse>
//
//    @GET("search/movie")
//    fun getSearchResult(@Query("api_key") apiKey: String,
//                        @Query("query") keyword: String): Single<SearchResponse>
//
//
//    @GET("movie/{id}")
//    fun getMovieDetail(@Path("id") movieId: Int,
//                       @Query("api_key") apiKey: String): Single<MovieDetailVo>

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