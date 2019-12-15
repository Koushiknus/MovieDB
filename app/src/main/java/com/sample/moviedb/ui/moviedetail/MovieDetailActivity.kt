package com.sample.moviedb.ui.moviedetail

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sample.moviedb.BuildConfig
import com.sample.moviedb.R
import com.sample.moviedb.base.Constants
import com.sample.moviedb.base.ViewModelFactory
import com.sample.moviedb.databinding.ActivityMovieDetailBinding
import com.sample.moviedb.model.Movie
import com.sample.moviedb.ui.movieList.MovieListAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_list.*


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var mMovieDetailViewModel: MovieDetailViewModel
    private val mAdapter = MovieListAdapter(this)
    private var mGridLayoutManager: GridLayoutManager? = null
    private lateinit var mMovieDetailBinding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMovieDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        initialData()
        initialObservers()

    }

    private fun initialData() {
        mMovieDetailViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(
            MovieDetailViewModel::class.java
        )
        getIntentExtra()
        showOrHideProgress(View.VISIBLE)
        mMovieDetailViewModel.getMovieDetail(mMovieDetailViewModel.mMovie?.id ?: 0)
        text_movie_overview.movementMethod = ScrollingMovementMethod()
        val columns = resources.getInteger(R.integer.movies_columns)
        mGridLayoutManager = GridLayoutManager(this, columns)
        btn_book_movie.setOnClickListener {
            navigateToCathay()
        }

    }

    fun navigateToCathay(){
        val uri: Uri = Uri.parse(BuildConfig.CATHAY_URL)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun initialObservers() {

        mMovieDetailViewModel.mListofMovies.observe(this, Observer {
            showOrHideProgress(View.GONE)
            if (it.size > 0) {
                mAdapter.setData(it)
                view_no_movies.visibility = View.GONE
            } else if(it.isEmpty()&&mMovieDetailViewModel.mPageCount ==1) {
               view_no_movies.visibility = View.VISIBLE
            }
        })
        mMovieDetailViewModel.mDuration.observe(this, Observer {
            showOrHideProgress(View.GONE)
            mMovieDetailBinding.textMovieDuration.text = mMovieDetailViewModel.getFormattedDuration(it)
        })
        mMovieDetailViewModel.mErrorOccured.observe(this, Observer {
            showOrHideProgress(View.GONE)
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
        })
        mAdapter.mEndReached.observe(this, Observer {
            mMovieDetailViewModel.mPageCount++
            showOrHideProgress(View.VISIBLE)
            //mMovieDetailViewModel.getRelatedMovies(mMovieDetailViewModel.mMovieId)

        })
        mAdapter.mMovieClicked.observe(this, Observer {
            Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(Constants.MOVIE_ID, it)
                startActivity(this)
            }
        })
    }

    private fun getIntentExtra() {
        mMovieDetailViewModel.mMovie = intent.getParcelableExtra(Constants.MOVIE_ID)
        mMovieDetailViewModel.mMovie?.let { movie ->
            setPoster(movie)
            mMovieDetailBinding.movie = movie
            mMovieDetailViewModel.mMovieId = movie.id
        }
    }

    private fun setPoster(movie: Movie?) {
        Glide.with(this)
            .load(Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_SIZE + movie?.poster_path)
            .placeholder(ColorDrawable(ContextCompat.getColor(this, R.color.accent_material_light)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(image_movie_detail_poster)

    }
    private fun showOrHideProgress(visibilty : Int){
//        progressBar.visibility = visibilty
    }
}
