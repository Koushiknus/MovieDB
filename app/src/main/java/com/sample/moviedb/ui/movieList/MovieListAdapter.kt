package com.sample.moviedb.ui.movieList

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sample.moviedb.R
import com.sample.moviedb.ui.model.Movie

class MovieListAdapter(private val ctx : Context): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    private val POSTER_IMAGE_SIZE = "w780"

    private val mListOfMovies = ArrayList<Movie>()

     val mEndReached = MutableLiveData<Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)
        return ViewHolder(itemView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val moviePoster = itemView.findViewById<ImageView>(R.id.image_movie_poster)

        fun bindView(
            movie: Movie,
            ctx: Context,
            posterImageBaseUrl: String,
            posterImageSize: String
        ){

            moviePoster.contentDescription = movie.title
            Glide.with(ctx)
                .load(posterImageBaseUrl + posterImageSize + movie.poster_path)
                .placeholder(ColorDrawable(ctx.getResources().getColor(R.color.accent_material_light)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(moviePoster)
        }
    }
    fun setData(data: MutableList<Movie>) {
        mListOfMovies.addAll(data)
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int {
      return  mListOfMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(mListOfMovies[position],ctx,POSTER_IMAGE_BASE_URL,POSTER_IMAGE_SIZE)
        if(position == itemCount-1){
            mEndReached.value = true
        }

    }
}