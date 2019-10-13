package com.sample.moviedb.di

import com.sample.moviedb.ui.movieList.MovieListRepository
import com.sample.moviedb.ui.moviedetail.MovieDetailRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object RepositoryModule {
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideMovieListRepository(): MovieListRepository {
        return MovieListRepository()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideMovieDetailRepository(): MovieDetailRepository {
        return MovieDetailRepository()
    }


}