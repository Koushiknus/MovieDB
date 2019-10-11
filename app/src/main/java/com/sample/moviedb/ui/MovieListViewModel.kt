package com.sample.moviedb.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.sample.moviedb.network.Api
import com.sample.moviedb.ui.model.MovieResponse
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(application: Application) : AndroidViewModel(application) {


    fun getListOfMovies(){
        val result= Api.run {
            getApiService().getAllMovies()
                .map(MovieResponse::getResults)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Log.v("MovieSizeIs",it.size.toString())
                }
        }
    }

    fun getRelatedMovies(){
        val result= Api.run {
            getApiService().getRelatedMovies(475557,3)
                .map(MovieResponse::getResults)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Log.v("RelatedMovieSizeIs",it.size.toString())
                }
        }
    }
}