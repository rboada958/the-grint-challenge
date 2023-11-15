package com.example.redditapp.core.di

import com.example.redditapp.data.api.RedditApi
import com.example.redditapp.data.repository.TopRepository
import com.example.redditapp.data.repository.TopRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    fun provideTopRepository(redditApi: RedditApi) : TopRepository =
        TopRepositoryImpl(redditApi)

}