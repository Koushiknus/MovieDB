package com.sample.moviedb.ui.moviedetail

import com.sample.moviedb.base.BaseRepository
import com.sample.moviedb.network.ApiMethods
import com.sample.moviedb.ui.model.Movie
import com.sample.moviedb.ui.model.MovieResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MovieDetailRepository : BaseRepository() {

    @set: Inject
    var mApiMethods : ApiMethods? = null

    fun getRelatedMovies(movieId : Long,pageCount : Int): Observable<ArrayList<Movie>>? {

     return   mApiMethods!!.getRelatedMovies(movieId,pageCount)
            .map(MovieResponse::getResults)
            .subscribeOn(Schedulers.io())

    }
}