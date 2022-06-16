package com.mrroboto.notimed.network

import okhttp3.ResponseBody


sealed class ApiResponse<T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Failure<T>(
        val errorCode: Int,
        val errorBody: String
    ) : ApiResponse<T>()
}
