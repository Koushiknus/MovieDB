package com.sample.moviedb.ui.moviedetail

import androidx.lifecycle.MutableLiveData
import com.sample.moviedb.base.BaseViewModel
import com.sample.moviedb.network.ApiMethods
import com.sample.moviedb.ui.model.Movie
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MovieDetailViewModel : BaseViewModel() {

    val mListofMovies = MutableLiveData<ArrayList<Movie>>()
    private  var mSubscription: Disposable? =null

    @set:Inject
    var mMovieDetailRepository : MovieDetailRepository? = null

    var mMovieId : Long = 0L

    @set: Inject
    var apiMethods : ApiMethods? = null

    var mPageCount = 1


    fun getRelatedMovies(movieId : Long){

        mSubscription =   mMovieDetailRepository?.getRelatedMovies(movieId,mPageCount)?.subscribe({
            mListofMovies.postValue(it)
        },{
            //error handling
        })

    }

    override fun onCleared() {
        super.onCleared()
        mSubscription?.dispose()
    }

}