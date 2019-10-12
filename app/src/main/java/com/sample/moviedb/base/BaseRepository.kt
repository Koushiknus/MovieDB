package com.sample.moviedb.base

import com.sample.moviedb.di.AppModule
import com.sample.moviedb.di.DaggerRepositoryInjector
import com.sample.moviedb.di.RepositoryInjector
import com.sample.moviedb.di.RepositoryModule
import com.sample.moviedb.ui.MovieListRepository

abstract class BaseRepository {

    private val injector: RepositoryInjector = DaggerRepositoryInjector
        .builder()
        .appModule(AppModule)
        .repositoryModule(RepositoryModule)
        .build()

    init {
        inject()
    }
    private fun inject(){
        when(this){
            is MovieListRepository ->injector.inject(this)
        }
    }
}