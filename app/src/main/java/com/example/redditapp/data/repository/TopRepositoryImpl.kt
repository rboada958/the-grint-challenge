package com.example.redditapp.data.repository

import com.example.redditapp.data.api.RedditApi
import com.example.redditapp.domain.entity.ResponseTop
import com.example.redditapp.utils.DataState
import com.example.redditapp.utils.runRemoteApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TopRepositoryImpl @Inject constructor(private val api: RedditApi) :
    TopRepository {
    override suspend fun top(page: String): Flow<DataState<ResponseTop>> =
        flow {
            emit(DataState.Loading)
            runRemoteApiCall(
                success = {
                    emit(DataState.Success(data = data))
                }
            ) {
                api.top(after = page)

            }
        }
}