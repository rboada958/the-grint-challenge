package com.example.redditapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @BindingAdapter("loadUrl")
    @JvmStatic
    fun loadImageRound(imageView: ImageView, url: String?) {
        url?.let { imageView.loadImage(it) }
    }
}