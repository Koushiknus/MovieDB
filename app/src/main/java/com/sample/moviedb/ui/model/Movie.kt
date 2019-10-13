package com.sample.moviedb.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie  (

    var id : Long ,

     val original_title: String?,

     val overview: String? ,

     val release_date: String? = "",

     val poster_path: String? = "",

     val popularity: Double = 0.0,

     var title: String? = null,

     val vote_average: Double = 0.0,

     val vote_count: Long = 0,

     val backdrop_path: String? = ""

): Parcelable