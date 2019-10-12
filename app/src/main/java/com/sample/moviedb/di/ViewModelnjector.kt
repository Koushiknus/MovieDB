package com.sample.moviedb.di

import com.sample.moviedb.ui.MovieListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), RepositoryModule::class])
interface ViewModelInjector {
    //Splash
    fun inject(movieListViewModel: MovieListViewModel)




    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun appModule(appModule: AppModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder
    }
}