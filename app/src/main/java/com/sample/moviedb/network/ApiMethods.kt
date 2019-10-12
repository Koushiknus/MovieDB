package com.sample.moviedb.network

import com.sample.moviedb.BuildConfig
import com.sample.moviedb.ui.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMethods{

    @GET("now_playing?"+ BuildConfig.BASE_API_KEY)
    fun getAllMovies(@Query("page")page : Int
    ): Observable<MovieResponse>

    @GET("{id}/similar?"+BuildConfig.BASE_API_KEY)
    fun getRelatedMovies(@Path("id")  movieId :Long,
                         @Query("page")page : Int) : Observable<MovieResponse>

}