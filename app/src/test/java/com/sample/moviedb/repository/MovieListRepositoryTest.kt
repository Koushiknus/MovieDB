package com.sample.moviedb.repository

import com.sample.moviedb.model.Movie
import com.sample.moviedb.ui.movieList.MovieListRepository
import com.sample.moviedb.util.InstantExecutorExtension
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class MovieListRepositoryTest {

    var movieListRepository : MovieListRepository? = MovieListRepository()


    @Test
    fun discoverMovies() {
        val results  = movieListRepository?.getTopMovies(1)
        val testObserver : TestObserver<ArrayList<Movie>>  =  results!!.test()
        testObserver.assertSubscribed()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }
}