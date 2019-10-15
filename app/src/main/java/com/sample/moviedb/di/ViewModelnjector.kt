package com.sample.moviedb.di

import com.sample.moviedb.ui.movieList.MovieListViewModel
import com.sample.moviedb.ui.moviedetail.MovieDetailViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), RepositoryModule::class])
interface ViewModelInjector {

    fun inject(movieListViewModel: MovieListViewModel)
    fun inject(movieDetailViewModel: MovieDetailViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun appModule(appModule: AppModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder
    }
}