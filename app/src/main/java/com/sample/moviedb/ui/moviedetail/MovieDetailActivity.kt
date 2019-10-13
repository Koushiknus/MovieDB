package com.sample.moviedb.ui.moviedetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sample.moviedb.R
import com.sample.moviedb.base.Constants
import com.sample.moviedb.base.ViewModelFactory
import com.sample.moviedb.ui.model.Movie

class MovieDetailActivity : AppCompatActivity() {

    lateinit var mMovieDetailViewModel: MovieDetailViewModel
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

    }

    private fun initialObservers(){

        mMovieDetailViewModel.mListofMovies.observe(this, Observer {

            Log.v("RelatedResult",it.size.toString())
        })
    }

    private fun getIntentExtra(){
       mMovie = intent.getParcelableExtra<Movie>(Constants.MOVIE_ID)
        Log.v("MoviewId",mMovie!!.title)

    }
}
