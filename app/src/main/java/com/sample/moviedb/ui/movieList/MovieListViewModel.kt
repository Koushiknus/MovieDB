package com.sample.moviedb.ui.movieList

import androidx.lifecycle.MutableLiveData
import com.sample.moviedb.base.BaseViewModel
import com.sample.moviedb.ui.MovieListRepository
import com.sample.moviedb.ui.model.Movie
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MovieListViewModel() : BaseViewModel() {

    val mListofMovies = MutableLiveData<ArrayList<Movie>>()
    private  var mSubscription: Disposable? =null

    @set:Inject
    var movieListRepository : MovieListRepository? = null

    var mPageCount = 1

    fun getListOfMovies(mPageCount: Int) {

        mSubscription = movieListRepository?.getListOfMovies(mPageCount)?.subscribe( {
            mListofMovies.postValue(it)
        },{
            //onFailure Handling
        })
    }

    override fun onCleared() {
        super.onCleared()
        mSubscription?.dispose()
    }

}