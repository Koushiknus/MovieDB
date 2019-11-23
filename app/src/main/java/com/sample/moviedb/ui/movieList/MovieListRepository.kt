package com.sample.moviedb.ui.movieList

import com.sample.moviedb.base.BaseRepository
import com.sample.moviedb.base.Constants
import com.sample.moviedb.model.Movie
import com.sample.moviedb.model.MovieResponse
import com.sample.moviedb.network.ApiMethods
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
    fun discoverMovies(mPageCount: Int): Observable<ArrayList<Movie>>? {

        mApiMethods?.let{ apiMethods ->
            return  apiMethods.discoverMovie("2016-12-31",Constants.RELEASE_DATE,mPageCount)
                .map(MovieResponse::getResults)
                .subscribeOn(Schedulers.io())

        }?: run {
            return null
        }
    }

}
