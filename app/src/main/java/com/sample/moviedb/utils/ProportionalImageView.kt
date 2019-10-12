package com.sample.moviedb.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

class ProportionalImageView : ImageView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height = Math.round(width * ASPECT_RATIO)
        setMeasuredDimension(width, height)
    }

    companion object {

        private val ASPECT_RATIO = 1.5f
    }
}