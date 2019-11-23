package com.sample.moviedb.ui.movieList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sample.moviedb.base.BaseViewModel
import com.sample.moviedb.model.Movie
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListViewModel() : BaseViewModel() {

    val mListofMovies = MutableLiveData<ArrayList<Movie>>()
    private  var mSubscription: Disposable? =null
    var mErrorOccured = MutableLiveData<Throwable>()


    @set:Inject
    var movieListRepository : MovieListRepository? = null

    var mPageCount = 1

    fun getListOfMovies(mPageCount: Int) {

        mSubscription = movieListRepository?.getListOfMovies(mPageCount)?.subscribeOn(Schedulers.io())?.subscribe( {
            mListofMovies.postValue(it)
        },{
            mErrorOccured.postValue(it)
        })
    }

    fun discoverMovies(){

        mSubscription = movieListRepository?.discoverMovies()?.subscribeOn(Schedulers.io())?.subscribe( {
            Log.v("ResultReceived",it.size.toString())
            mListofMovies.postValue(it)

        },{
            mErrorOccured.postValue(it)
        })
    }

    override fun onCleared() {
        super.onCleared()
        mSubscription?.dispose()
    }

}