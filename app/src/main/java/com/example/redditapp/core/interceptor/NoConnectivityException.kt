package com.example.redditapp.core.interceptor

import com.example.redditapp.R
import com.example.redditapp.utils.Commons
import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = Commons.getString(R.string.connectivity_exception)
}
