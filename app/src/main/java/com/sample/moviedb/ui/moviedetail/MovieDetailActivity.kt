package com.sample.moviedb.ui.moviedetail

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sample.moviedb.R
import com.sample.moviedb.base.Constants
import com.sample.moviedb.base.ViewModelFactory
import com.sample.moviedb.databinding.ActivityMovieDetailBinding
import com.sample.moviedb.ui.model.Movie
import com.sample.moviedb.ui.movieList.MovieListAdapter
import com.sample.moviedb.utils.ItemOffsetDecoration
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.layout_progressbar.*

class MovieDetailActivity : AppCompatActivity() {

    lateinit var mMovieDetailViewModel: MovieDetailViewModel
    private val mAdapter = MovieListAdapter(this)
    private var gridLayoutManager: GridLayoutManager? = null

    var mMovie : Movie? =null
    lateinit var mMovieDetailBinding : ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMovieDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie_detail)
        initialData()
        initialObservers()

    }

    private fun initialData(){
        mMovieDetailViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(
            MovieDetailViewModel::class.java)
        getIntentExtra()
        progressBar.visibility = View.VISIBLE
        mMovieDetailViewModel.getRelatedMovies(mMovie!!.id)
        val mLayoutManager = LinearLayoutManager(this)
        movie_similar.adapter = mAdapter
        movie_similar.setHasFixedSize(true)
        movie_similar.layoutManager = mLayoutManager
        movie_similar.setItemAnimator(DefaultItemAnimator())
        movie_similar.addItemDecoration(
            ItemOffsetDecoration(
                this,
                R.dimen.movie_item_offset
            )
        )
        val columns = resources.getInteger(R.integer.movies_columns)
        gridLayoutManager = GridLayoutManager(this, columns)
        movie_similar.layoutManager = gridLayoutManager

    }

    private fun initialObservers(){

        mMovieDetailViewModel.mListofMovies.observe(this, Observer {

            progressBar.visibility = View.GONE
            if(it.size>0){
                mAdapter.setData(it)
                view_no_movies.visibility = View.GONE
            }else{
                view_no_movies.visibility = View.VISIBLE
            }
        })
        mAdapter.mEndReached.observe(this, Observer {
            Log.v("EndReached",it.toString())
            mMovieDetailViewModel.mPageCount++
            progressBar.visibility = View.VISIBLE

            mMovieDetailViewModel.getListOfMovies(mMovieDetailViewModel.mPageCount)

        })
        mAdapter.mMovieClicked.observe(this, Observer {
            Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(Constants.MOVIE_ID,it)
                startActivity(this)
            }
        })
    }

    private fun getIntentExtra(){
       mMovie = intent.getParcelableExtra<Movie>(Constants.MOVIE_ID)
        setPoster(mMovie)
        mMovieDetailBinding.movie = mMovie

    }

    private fun setPoster(movie : Movie?){
        Glide.with(this)
            .load(Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_SIZE + movie?.poster_path)
            .placeholder(ColorDrawable(ContextCompat.getColor(this, R.color.accent_material_light)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(image_movie_detail_poster)

    }
}
