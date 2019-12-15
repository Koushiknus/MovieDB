package com.sample.moviedb.ui.movieList

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

    fun discoverMovies(mPageCount: Int){

        mSubscription = movieListRepository?.discoverMovies(mPageCount)?.subscribeOn(Schedulers.io())?.subscribe( {
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