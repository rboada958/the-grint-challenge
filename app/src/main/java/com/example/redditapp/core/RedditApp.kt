package com.example.redditapp.core

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RedditApp : Application() {

    companion object {
        lateinit var applicationInstance: RedditApp

        val appContext: Context by lazy {
            applicationInstance.applicationContext
        }
        var moveMyPoint = true
    }

    override fun onCreate() {
        super.onCreate()
        applicationInstance = this
    }
}