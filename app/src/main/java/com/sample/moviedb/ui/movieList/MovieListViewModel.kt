package com.sample.moviedb.ui.movieList

import androidx.lifecycle.MutableLiveData
import com.sample.moviedb.base.BaseViewModel
import com.sample.moviedb.base.Constants
import com.sample.moviedb.model.Movie
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListViewModel() : BaseViewModel() {

    val mListofMovies = MutableLiveData<ArrayList<Movie>>()
    private  var mSubscription: Disposable? =null
    var mErrorOccured = MutableLiveData<Throwable>()
    var mMovieListCache = ArrayList<Movie>()
    var mCurrentSetting = Constants.SETTING_NAME


    @set:Inject
    var movieListRepository : MovieListRepository? = null

    var mPageCount = 1

    fun getTopMovies(mPageCount: Int){

        mSubscription = movieListRepository?.getTopMovies(mPageCount)?.subscribeOn(Schedulers.io())?.subscribe( {
            mListofMovies.postValue(it)
            mMovieListCache.clear()
            mMovieListCache.addAll(it)


        },{
            mErrorOccured.postValue(it)
        })
    }

    fun sortMovieByName(): List<Movie> {
       return mMovieListCache.sortedBy { list -> list.name}
    }

    fun sortMovieByDate() : List<Movie>{
        return mMovieListCache.sortedBy { list ->list.release_date }
    }

    override fun onCleared() {
        super.onCleared()
        mSubscription?.dispose()
    }

}