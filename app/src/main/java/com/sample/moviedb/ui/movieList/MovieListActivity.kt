package com.sample.moviedb.ui.movieList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.moviedb.base.Constants
import com.sample.moviedb.base.ViewModelFactory
import com.sample.moviedb.ui.moviedetail.MovieDetailActivity
import com.sample.moviedb.utils.ItemOffsetDecoration
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.layout_progressbar.*


class MovieListActivity : AppCompatActivity() {

    private lateinit var mMovieListViewModel : MovieListViewModel
    private val mAdapter = MovieListAdapter(this)
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sample.moviedb.R.layout.activity_movie_list)
        initialData()
        initialObservers()
    }

    private fun initialData(){
        mMovieListViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(
            MovieListViewModel::class.java)
        progressBar.visibility = View.VISIBLE
        mMovieListViewModel.getListOfMovies(mMovieListViewModel.mPageCount)
        val mLayoutManager = LinearLayoutManager(this)
        movies_grid.adapter = mAdapter
        movies_grid.setHasFixedSize(true)
        movies_grid.layoutManager = mLayoutManager
        movies_grid.setItemAnimator(DefaultItemAnimator())
        movies_grid.addItemDecoration(
            ItemOffsetDecoration(
                this,
                com.sample.moviedb.R.dimen.movie_item_offset
            )
        )

        val columns = resources.getInteger(com.sample.moviedb.R.integer.movies_columns)
        gridLayoutManager = GridLayoutManager(this, columns)
        movies_grid.setLayoutManager(gridLayoutManager)

        // mMovieListViewModel.getRelatedMovies()
    }

    private fun initialObservers(){
       mMovieListViewModel.mListofMovies.observe(this, Observer {
           Log.v("ObserverInvoked",it.size.toString())
           progressBar.visibility = View.GONE
           mAdapter.setData(it)

       })
        mAdapter.mEndReached.observe(this, Observer {
            Log.v("EndReached",it.toString())
            mMovieListViewModel.mPageCount++
            progressBar.visibility = View.VISIBLE
            mMovieListViewModel.getListOfMovies(mMovieListViewModel.mPageCount)

        })
        mAdapter.mMovieClicked.observe(this, Observer {

            Log.v("MovieReceived",it.original_title)
            Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(Constants.MOVIE_ID,it)
                startActivity(this)
            }
        })
    }

}
