package com.example.redditapp.data.api


import com.example.redditapp.domain.entity.ResponseTop
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("top.json")
    suspend fun top(
        @Query("limit") limit: Int = 10,
        @Query("after") after: String
    ): ApiResponse<ResponseTop>
}