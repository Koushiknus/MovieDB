package com.sample.moviedb.ui.movieList

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.moviedb.R
import com.sample.moviedb.base.ViewModelFactory
import com.sample.moviedb.ui.MovieListViewModel
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    private lateinit var mMovieListViewModel : MovieListViewModel
    private val mAdapter = MovieListAdapter(this)
    private var gridLayoutManager: GridLayoutManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        initialData()
        initialObservers()
    }

    private fun initialData(){
        mMovieListViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(MovieListViewModel::class.java)
        mMovieListViewModel.getListOfMovies()
        val mLayoutManager = LinearLayoutManager(this)
        movies_grid.setHasFixedSize(true)
        movies_grid.layoutManager = mLayoutManager
        movies_grid.setItemAnimator(DefaultItemAnimator())
        val columns = resources.getInteger(R.integer.movies_columns)
        gridLayoutManager = GridLayoutManager(this, columns)
        movies_grid.setLayoutManager(gridLayoutManager)

        // mMovieListViewModel.getRelatedMovies()
    }

    private fun initialObservers(){
       mMovieListViewModel.mListofMovies.observe(this, Observer {
           Log.v("ObserverInvoked",it.size.toString())
           mAdapter.setData(it)
           movies_grid.adapter = mAdapter
           mAdapter.notifyDataSetChanged()

       })
    }

}
