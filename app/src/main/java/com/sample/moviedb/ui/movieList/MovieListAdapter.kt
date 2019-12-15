package com.sample.moviedb.ui.movieList

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sample.moviedb.R
import com.sample.moviedb.base.Constants
import com.sample.moviedb.databinding.MovieListBinding
import com.sample.moviedb.model.Movie
import kotlinx.android.synthetic.main.movie_list.view.*

class MovieListAdapter(private val ctx: Context) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val mListOfMovies = ArrayList<Movie>()
    val mEndReached = MutableLiveData<Boolean>()
    var mMovieClicked = MutableLiveData<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = MovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    class ViewHolder(itemView: MovieListBinding) : RecyclerView.ViewHolder(itemView.root) {


        fun bindView(
            movie: Movie,
            ctx: Context,
            posterImageBaseUrl: String,
            posterImageSize: String
        ) {

            itemView.image_movie_poster.contentDescription = movie.title
            Glide.with(ctx)
                .load(posterImageBaseUrl + posterImageSize + movie.poster_path)
                .placeholder(
                    ColorDrawable(
                        ContextCompat.getColor(
                            ctx,
                            R.color.accent_material_light
                        )
                    )
                )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(itemView.image_movie_poster)
            itemView.title.text = movie.title
            itemView.txt_popularity.text = movie.popularity?.toString()

        }

    }

    fun setData(data: MutableList<Movie>) {
        mListOfMovies.addAll(data)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return mListOfMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(
            mListOfMovies[position],
            ctx,
            Constants.POSTER_IMAGE_BASE_URL,
            Constants.POSTER_IMAGE_SIZE
        )
        if (position == itemCount - 1) {
            mEndReached.value = true
        }
        holder.itemView.image_movie_poster.setOnClickListener {
            mMovieClicked.postValue(mListOfMovies[position])
        }

    }
}