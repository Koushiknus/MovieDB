package com.sample.moviedb.ui.moviedetail

import com.sample.moviedb.base.BaseRepository
import com.sample.moviedb.model.Movie
import com.sample.moviedb.model.MovieDetail
import com.sample.moviedb.model.MovieResponse
import com.sample.moviedb.network.ApiMethods
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MovieDetailRepository : BaseRepository() {

    @set: Inject
    var mApiMethods : ApiMethods? = null

    fun getMovieDetail(movieId : Long): Observable<MovieDetail>? {
       mApiMethods?.let {apiMethods ->
            return   apiMethods.getMovieDetail(movieId)
                .subscribeOn(Schedulers.io())
        }  ?: run {
            return null
        }
    }
}