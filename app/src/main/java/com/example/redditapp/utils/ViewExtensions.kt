package com.example.redditapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.redditapp.R

fun ImageView.loadImage(urlImage: String?) {
    Glide.with(context)
        .load(urlImage)
        .placeholder(R.drawable.image)
        .into(this)
}