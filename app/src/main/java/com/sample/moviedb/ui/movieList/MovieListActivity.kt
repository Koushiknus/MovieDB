package com.sample.moviedb.ui.movieList

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.moviedb.base.ViewModelFactory
import com.sample.moviedb.ui.MovieListViewModel
import com.sample.moviedb.utils.ItemOffsetDecoration
import kotlinx.android.synthetic.main.activity_movie_list.*


class MovieListActivity : AppCompatActivity() {

    private lateinit var mMovieListViewModel : MovieListViewModel
    private val mAdapter = MovieListAdapter(this)
    private var gridLayoutManager: GridLayoutManager? = null

    private var loading = true
    var pastVisiblesItems: Int = 0
    var visibleItemCount:Int = 0
    var totalItemCount:Int = 0
    private var visibleThreshold = 5
    private var previousTotal = 0
    var firstVisibleItem: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sample.moviedb.R.layout.activity_movie_list)
        initialData()
        initialObservers()
    }

    private fun initialData(){
        mMovieListViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(MovieListViewModel::class.java)
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

        movies_grid.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = movies_grid.getChildCount()
                totalItemCount = mLayoutManager.itemCount
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition()

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                    // End has been reached

                    Log.v("Yaeye!", "end called")

                    // Do something

                    loading = true
                }
            }
        })

        val columns = resources.getInteger(com.sample.moviedb.R.integer.movies_columns)
        gridLayoutManager = GridLayoutManager(this, columns)
        movies_grid.setLayoutManager(gridLayoutManager)

        // mMovieListViewModel.getRelatedMovies()
    }

    private fun initialObservers(){
       mMovieListViewModel.mListofMovies.observe(this, Observer {
           Log.v("ObserverInvoked",it.size.toString())
           mAdapter.setData(it)

       })
        mAdapter.mEndReached.observe(this, Observer {
            Log.v("EndReached",it.toString())
            mMovieListViewModel.mPageCount++
            mMovieListViewModel.getListOfMovies(mMovieListViewModel.mPageCount)

        })
    }

}
