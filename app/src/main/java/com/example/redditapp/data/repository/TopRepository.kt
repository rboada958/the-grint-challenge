package com.example.redditapp.data.repository

import com.example.redditapp.domain.entity.ResponseTop
import com.example.redditapp.utils.DataState
import kotlinx.coroutines.flow.Flow

interface TopRepository {

    suspend fun top(page: String): Flow<DataState<ResponseTop>>
}