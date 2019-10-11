package com.sample.moviedb.ui.model

import java.util.*

class MovieResponse {
    private val results  =  ArrayList<Movie>()

    fun getResults(): ArrayList<Movie> {
        return results
    }

}