package com.sample.moviedb.ui.movieList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sample.moviedb.base.BaseViewModel
import com.sample.moviedb.network.ApiMethods
import com.sample.moviedb.ui.model.Movie
import com.sample.moviedb.ui.model.MovieResponse
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListViewModel() : BaseViewModel() {

    val mListofMovies = MutableLiveData<ArrayList<Movie>>()

    @set: Inject
    var apiMethods : ApiMethods? = null

    var mPageCount = 1

    fun getListOfMovies(mPageCount: Int) {
       /* val result= Api.run {
            getApiService().getAllMovies()
                .map(MovieResponse::getResults)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Log.v("MovieSizeIs",it.size.toString())
                }
        }*/
        apiMethods!!.getAllMovies(this.mPageCount)
            .map(MovieResponse::getResults)
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.v("MovieSizeIs",it.size.toString())
                mListofMovies.postValue(it)
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