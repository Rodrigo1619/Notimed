package com.mrroboto.notimed.network

sealed class ApiResponse<T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Failure<T>(
        val errorCode: Int,
        val errorBody: String
    ) : ApiResponse<T>()
    data class Loading<T>(val isLoading: T) : ApiResponse<T>()
}