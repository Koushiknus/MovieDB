package com.sample.moviedb.di

import com.sample.moviedb.ui.MovieListRepository
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


}