package com.example.redditapp.utils

import com.example.redditapp.core.RedditApp

object Commons {

    fun getString(string : Int) : String {
        return RedditApp.appContext.getString(string)
    }
}