package com.example.redditapp.utils

import androidx.annotation.LayoutRes

interface ItemViewHolder {
    @get:LayoutRes
    val layoutId: Int
    val viewType : Int
        get() = 0
}

