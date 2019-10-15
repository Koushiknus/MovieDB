package com.sample.moviedb.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.moviedb.ui.movieList.MovieListViewModel
import com.sample.moviedb.ui.moviedetail.MovieDetailViewModel
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory(context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieListViewModel::class.java))
            return MovieListViewModel() as T
        if(modelClass.isAssignableFrom(MovieDetailViewModel::class.java))
            return MovieDetailViewModel() as T

        throw IllegalArgumentException("Unknown ViewModel class")

    }
}