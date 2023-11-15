package com.example.redditapp.core.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val context: Context) : Interceptor {

    companion object {
        @JvmStatic
        fun isOnline(c: Context): Boolean {
            val connectivityManager: ConnectivityManager = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            with(connectivityManager){
                val networkCapabilities = getNetworkCapabilities(activeNetwork)
                return networkCapabilities != null &&
                        ( networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN))
            }
        }
    }

    @Throws(IOException::class)
    override
    fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline(context)) {
           throw NoConnectivityException()
        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}