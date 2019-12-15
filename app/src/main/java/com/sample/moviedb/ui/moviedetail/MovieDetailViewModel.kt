package com.sample.moviedb.ui.moviedetail

import androidx.lifecycle.MutableLiveData
import com.sample.moviedb.base.BaseViewModel
import com.sample.moviedb.model.Movie
import com.sample.moviedb.network.ApiMethods
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MovieDetailViewModel : BaseViewModel() {
    var mMovie: Movie? = null
    val mDuration = MutableLiveData<String>()
    private var mSubscription: Disposable? = null

    @set:Inject
    var mMovieDetailRepository: MovieDetailRepository? = null
    var mMovieId: Long = 0L

    @set: Inject
    var apiMethods: ApiMethods? = null
    var mPageCount = 1
    var mErrorOccured = MutableLiveData<Throwable>()


    fun getMovieDetail(movieId: Long) {
        mSubscription = mMovieDetailRepository?.getMovieDetail(movieId)?.subscribe({
            mDuration.postValue(it.runtime.toString())
        }, {
            //error handling
            mErrorOccured.postValue(it)

        })
    }

    fun getFormattedDuration(duration: String): String {
        return "Duration : $duration Minutes" ?: "Not Available"
    }

    override fun onCleared() {
        super.onCleared()
        mSubscription?.dispose()
    }

}