package com.sample.moviedb.repository

import com.sample.moviedb.base.BaseRepository
import com.sample.moviedb.model.Movie
import com.sample.moviedb.network.ApiMethods
import com.sample.moviedb.ui.movieList.MovieListRepository
import com.sample.moviedb.util.InstantExecutorExtension
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import javax.inject.Inject

@ExtendWith(InstantExecutorExtension::class)
class MovieListRepositoryTest {

    var movieListRepository : MovieListRepository? = MovieListRepository()


    @Test
    fun discoverMovies() {
        val results  = movieListRepository?.discoverMovies(1)
        val testObserver : TestObserver<ArrayList<Movie>>  =  results!!.test()
        testObserver.assertSubscribed()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }
}