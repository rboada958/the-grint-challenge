package com.example.redditapp.domain.entity

data class RequestAccessToken(
    val grant_type: String,
    val password: String,
    val username: String
)