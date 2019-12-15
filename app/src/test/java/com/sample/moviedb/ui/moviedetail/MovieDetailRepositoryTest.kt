package com.sample.moviedb.ui.moviedetail

import com.sample.moviedb.model.Movie
import com.sample.moviedb.model.MovieDetail
import com.sample.moviedb.ui.movieList.MovieListRepository
import io.reactivex.observers.TestObserver
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MovieDetailRepositoryTest {

    var movieDetailRepository  = MovieDetailRepository()

    @Test
    fun getMovieDetail() {
        val results = movieDetailRepository.getMovieDetail(1)
        val testObserver : TestObserver<MovieDetail> =  results!!.test()
        testObserver.assertSubscribed()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }
}