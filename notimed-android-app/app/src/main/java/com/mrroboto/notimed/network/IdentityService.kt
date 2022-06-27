package com.mrroboto.notimed.network

import com.google.gson.annotations.SerializedName
import com.mrroboto.notimed.network.dto.LoginRequest
import com.mrroboto.notimed.network.dto.LoginResponse
import com.mrroboto.notimed.network.dto.UserDto
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface IdentityService {
    @Headers("Content-Type: application/json")
    @POST("/identity/signin")
    suspend fun loginAsync(
        @Body credentials: LoginRequest
        ): LoginResponse
}