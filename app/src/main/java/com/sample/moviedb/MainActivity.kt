package com.sample.moviedb

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sample.moviedb.ui.MovieListViewModel

class MainActivity : AppCompatActivity() {

    private val mMovieListViewModel : MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMovieListViewModel.getListOfMovies()
        mMovieListViewModel.getRelatedMovies()
    }
}
