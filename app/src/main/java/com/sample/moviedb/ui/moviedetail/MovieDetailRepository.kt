package com.sample.moviedb.ui.moviedetail

import com.sample.moviedb.base.BaseRepository
import com.sample.moviedb.model.Movie
import com.sample.moviedb.model.MovieResponse
import com.sample.moviedb.network.ApiMethods
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MovieDetailRepository : BaseRepository() {

    @set: Inject
    var mApiMethods : ApiMethods? = null

    fun getRelatedMovies(movieId : Long,pageCount : Int): Observable<ArrayList<Movie>>? {
              mApiMethods?.let {apiMethods ->
                  return   apiMethods.getRelatedMovies(movieId,pageCount)
                      .map(MovieResponse::getResults)
                      .subscribeOn(Schedulers.io())
              }  ?: run {
                  return null
              }
    }
}