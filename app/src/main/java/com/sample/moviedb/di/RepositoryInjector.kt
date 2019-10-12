package com.sample.moviedb.di

import com.sample.moviedb.ui.MovieListRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), RepositoryModule::class])
interface RepositoryInjector {

    fun inject(movieListRepository: MovieListRepository)

    @Component.Builder
    interface Builder {
        fun build(): RepositoryInjector
        fun appModule(appModule: AppModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder
    }
}