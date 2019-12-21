package com.sample.moviedb.ui.movieList

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.moviedb.R
import com.sample.moviedb.base.Constants
import com.sample.moviedb.base.ViewModelFactory
import com.sample.moviedb.ui.moviedetail.MovieDetailActivity
import com.sample.moviedb.utils.ItemOffsetDecoration
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.layout_progressbar.*


class MovieListActivity : AppCompatActivity() {

    private lateinit var mMovieListViewModel : MovieListViewModel
    private val mAdapter = MovieListAdapter(this)
    private var mGridLayoutManager: GridLayoutManager? = null
    private var mSearchView : androidx.appcompat.widget.SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sample.moviedb.R.layout.activity_movie_list)
        initialData()
        initialObservers()
    }

    private fun initialData(){
        mMovieListViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(
            MovieListViewModel::class.java)
        showOrHideProgress(View.VISIBLE)
        mMovieListViewModel.getListOfMovies(mMovieListViewModel.mPageCount)
        val mLayoutManager = LinearLayoutManager(this)
        movies_grid.adapter = mAdapter
        movies_grid.setHasFixedSize(true)
        movies_grid.layoutManager = mLayoutManager
        movies_grid.itemAnimator = DefaultItemAnimator()
        movies_grid.addItemDecoration(
            ItemOffsetDecoration(
                this,
                com.sample.moviedb.R.dimen.movie_item_offset
            )
        )

        val columns = resources.getInteger(com.sample.moviedb.R.integer.movies_columns)
        mGridLayoutManager = GridLayoutManager(this, columns)
        movies_grid.layoutManager = mGridLayoutManager
    }

    private fun initialObservers(){
       mMovieListViewModel.mListofMovies.observe(this, Observer {
           showOrHideProgress(View.GONE)
           mAdapter.setData(it)

       })
        mMovieListViewModel.mErrorOccured.observe(this, Observer {
            showOrHideProgress(View.GONE)
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
        })
        mAdapter.mEndReached.observe(this, Observer {
            mMovieListViewModel.mPageCount++
            showOrHideProgress(View.VISIBLE)
            mMovieListViewModel.getListOfMovies(mMovieListViewModel.mPageCount)

        })
        mAdapter.mMovieClicked.observe(this, Observer {
            Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(Constants.MOVIE_ID,it)
                startActivity(this)
            }
        })
    }
    private val mSearchViewListener = object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            mSearchView?.clearFocus()
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            // view model code
            return true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.menu_movie_list,menu)
        val searchItem = menu?.findItem(R.id.action_search)
        mSearchView = searchItem?.actionView as androidx.appcompat.widget.SearchView
        mSearchView?.setOnQueryTextListener(mSearchViewListener)
        return super.onCreateOptionsMenu(menu)

    }



    private fun showOrHideProgress(visibilty : Int){
        progressBar.visibility = visibilty
    }

}
