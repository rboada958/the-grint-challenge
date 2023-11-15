package com.example.redditapp.core.di

import com.example.redditapp.data.api.RedditApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
@InstallIn(SingletonComponent::class)
class AppApiModule {

    @Provides
    fun provideRedditApi(retrofit: Retrofit): RedditApi = retrofit.create(RedditApi::class.java)

}