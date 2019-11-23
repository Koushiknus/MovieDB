package com.sample.moviedb.network

import com.sample.moviedb.BuildConfig
import com.sample.moviedb.model.MovieDetail
import com.sample.moviedb.model.MovieResponse
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

    @GET("discover/movie?"+BuildConfig.BASE_API_KEY2)
    fun discoverMovie(@Query("primary_release_date.lte")releaseDate : String,
                      @Query("sort_by")sortBy : String,
                      @Query("page") page : Int) : Observable<MovieResponse>

    @GET("movie/{id}?"+BuildConfig.BASE_API_KEY2)
    fun getMovieDetail(@Path("id")  movieId :Long) : Observable<MovieDetail>

}