package com.example.redditapp.presentation.adapter

import com.example.redditapp.R
import com.example.redditapp.domain.entity.Children
import com.example.redditapp.utils.ItemViewHolder

class ViewHolder(val result: Children, val onClick : ((Children) -> Unit)? = null) :
    ItemViewHolder {
    override val layoutId: Int = R.layout.post_layout
    override val viewType: Int = -3
}