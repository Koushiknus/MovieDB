package com.sample.moviedb.ui.movieList

import com.sample.moviedb.base.BaseRepository
import com.sample.moviedb.network.ApiMethods
import com.sample.moviedb.ui.model.Movie
import com.sample.moviedb.ui.model.MovieResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MovieListRepository : BaseRepository(){

 @set: Inject
 var mApiMethods : ApiMethods? = null

 fun getListOfMovies(mPageCount: Int): Observable<ArrayList<Movie>>? {
    mApiMethods?.let { apiMethods ->
        return  apiMethods.getAllMovies(mPageCount)
            .map(MovieResponse::getResults)
            .subscribeOn(Schedulers.io())

    }?: run {
        return null
    }
 }

}