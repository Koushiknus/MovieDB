package com.sample.moviedb.ui

import android.util.Log
import com.sample.moviedb.base.BaseViewModel
import com.sample.moviedb.network.ApiMethods
import com.sample.moviedb.ui.model.MovieResponse
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListViewModel() : BaseViewModel() {

    @set: Inject
    var apiMethods : ApiMethods? = null

    fun getListOfMovies(){
       /* val result= Api.run {
            getApiService().getAllMovies()
                .map(MovieResponse::getResults)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Log.v("MovieSizeIs",it.size.toString())
                }
        }*/
        apiMethods!!.getAllMovies()
            .map(MovieResponse::getResults)
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.v("MovieSizeIs",it.size.toString())
            }
    }

    fun getRelatedMovies(){
    /*    val result= Api.run {
            getApiService().getRelatedMovies(475557,3)
                .map(MovieResponse::getResults)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Log.v("RelatedMovieSizeIs",it.size.toString())
                }
        }*/
        apiMethods!!.getRelatedMovies(475557,3)
            .map(MovieResponse::getResults)
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.v("RelatedMovieSizeIs",it.size.toString())
            }
    }
}