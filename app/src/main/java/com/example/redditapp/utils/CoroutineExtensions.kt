package com.example.redditapp.utils

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.FlowCollector
import org.json.JSONObject

suspend fun <Q> FlowCollector<DataState<Q>>.runRemoteApiCall(
    success: suspend ApiResponse.Success<Q>.() -> Unit,
    error: suspend ApiResponse.Failure.Error<Q>.() -> Unit = {
        try {
            val jObjError = JSONObject(errorBody?.string())
            emit(DataState.OtherError(jObjError.getJSONObject("error").getString("message")))
        }catch (e: Exception){
            emit(DataState.OtherError("Something unexpected happened. Please try again later."))
        }
    },
    exception : suspend ApiResponse.Failure.Exception<Q>.() -> Unit = {
        this.exception.printStackTrace()
        if (this.exception.message!!.contains("Unable to resolve host"))
            emit(DataState.OtherError("We are unable to process your request."))
        else emit(DataState.Error(this.exception))
    },
    apiCall: suspend () -> ApiResponse<Q>,
) =
    this.run {
        apiCall.invoke()
            .suspendOnSuccess(success)
            .suspendOnError(error)
            .suspendOnException(exception)
    }