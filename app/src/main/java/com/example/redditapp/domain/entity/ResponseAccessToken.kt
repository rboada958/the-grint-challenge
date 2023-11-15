package com.example.redditapp.domain.entity

data class ResponseAccessToken(
    val access_token: String,
    val expires_in: Int,
    val scope: String,
    val token_type: String
)