package com.sample.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sample.moviedb.base.ViewModelFactory
import com.sample.moviedb.ui.movieList.MovieListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mMovieListViewModel : MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMovieListViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(MovieListViewModel::class.java)
       // mMovieListViewModel.getListOfMovies()
    }
}
