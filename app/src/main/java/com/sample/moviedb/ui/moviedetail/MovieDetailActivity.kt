package com.sample.moviedb.ui.moviedetail

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
import com.sample.moviedb.ui.model.Movie
import com.sample.moviedb.ui.movieList.MovieListAdapter
import com.sample.moviedb.utils.ItemOffsetDecoration
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    lateinit var mMovieDetailViewModel: MovieDetailViewModel
    private val mAdapter = MovieListAdapter(this)
    private var gridLayoutManager: GridLayoutManager? = null


    var mMovie : Movie? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initialData()
        initialObservers()

    }

    private fun initialData(){
        mMovieDetailViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(
            MovieDetailViewModel::class.java)
        getIntentExtra()
        mMovieDetailViewModel.getRelatedMovies(mMovie!!.id)
        val mLayoutManager = LinearLayoutManager(this)
        movie_videos.adapter = mAdapter
        movie_videos.setHasFixedSize(true)
        movie_videos.layoutManager = mLayoutManager
        movie_videos.setItemAnimator(DefaultItemAnimator())
        movie_videos.addItemDecoration(
            ItemOffsetDecoration(
                this,
                R.dimen.movie_item_offset
            )
        )

        val columns = resources.getInteger(com.sample.moviedb.R.integer.movies_columns)
        gridLayoutManager = GridLayoutManager(this, columns)
        movie_videos.setLayoutManager(gridLayoutManager)

    }

    private fun initialObservers(){

        mMovieDetailViewModel.mListofMovies.observe(this, Observer {

            Log.v("RelatedResult",it.size.toString())
            mAdapter.setData(it)
        })
    }

    private fun getIntentExtra(){
       mMovie = intent.getParcelableExtra<Movie>(Constants.MOVIE_ID)
        Log.v("MoviewId",mMovie!!.title)
        setTestData(mMovie)

    }

    private fun setTestData(movie : Movie?){

        Glide.with(this)
            .load(Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_SIZE + movie?.poster_path)
            .placeholder(ColorDrawable(getResources().getColor(R.color.accent_material_light)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(image_movie_detail_poster)


    }
}
