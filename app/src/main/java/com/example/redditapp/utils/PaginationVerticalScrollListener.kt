package com.example.redditapp.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationVerticalScrollListener
/**
 * Supporting only LinearLayoutManager for now.
 *
 * @param layoutManager
 */(var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = recyclerView.adapter!!.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition()

        if (!isLoading() && !isLastPage() && dy > 0) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

}