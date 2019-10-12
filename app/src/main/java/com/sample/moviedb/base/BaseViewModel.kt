package com.sample.moviedb.base

import androidx.lifecycle.ViewModel
import com.sample.moviedb.di.AppModule
import com.sample.moviedb.di.DaggerViewModelInjector
import com.sample.moviedb.di.RepositoryModule
import com.sample.moviedb.di.ViewModelInjector
import com.sample.moviedb.ui.MovieListViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .appModule(AppModule)
        .repositoryModule(RepositoryModule)
        .build()

    init {
        inject()

    }

    private fun inject(){
        when (this) {
            //splash
            is MovieListViewModel -> injector.inject(this)

        }
    }
}