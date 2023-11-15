package com.example.redditapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.redditapp.databinding.PostLayoutBinding
import com.example.redditapp.domain.entity.Children

class HomeAdapter(
    private var items: MutableList<Children?>,
    private val listener: ClickListener? = null
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(
        private val binding: PostLayoutBinding,
        private val listener: ClickListener? = null
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Children) {
            binding.itemHolder = ViewHolder(item) {
                listener?.onClicked(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            PostLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(items[position]!!)

    override fun getItemCount(): Int =
        items.size

    fun addItems(list: List<Children?>) {
        val positionStart = items.size
        items.addAll(list)
        notifyItemRangeInserted(positionStart, items.size)
    }

    interface ClickListener {
        fun onClicked(item: Children)
    }
}