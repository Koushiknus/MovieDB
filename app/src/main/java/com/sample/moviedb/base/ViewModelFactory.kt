package com.sample.moviedb.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.moviedb.ui.MovieListViewModel
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory(context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //Splash
        if(modelClass.isAssignableFrom(MovieListViewModel::class.java))
            return MovieListViewModel() as T


        throw IllegalArgumentException("Unknown ViewModel class")

    }
}