package com.example.redditapp.core.di

import android.content.Context
import com.example.redditapp.core.interceptor.ConnectivityInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val OK_HTTP_CACHE = "okhttp_cache"
    }

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideConnectivityInterceptor(@ApplicationContext context: Context): ConnectivityInterceptor {
        return ConnectivityInterceptor(context)
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {


        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader("x-custom-lang", Locale.getDefault().language)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .readTimeout(40, TimeUnit.MINUTES)
            .connectTimeout(40, TimeUnit.MINUTES)
            .callTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()
    }
}