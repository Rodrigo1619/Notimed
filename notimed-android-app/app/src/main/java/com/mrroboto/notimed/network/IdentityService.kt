package com.mrroboto.notimed.network

import com.mrroboto.notimed.network.responses.identity.LoginRequest
import com.mrroboto.notimed.network.responses.identity.LoginResponse
import com.mrroboto.notimed.network.responses.identity.RegisterRequest
import retrofit2.http.*

interface IdentityService {
    @Headers("Content-Type: application/json")
    @POST("/identity/signin")
    suspend fun loginAsync(
        @Body credentials: LoginRequest
        ): LoginResponse

    @Headers("Content-Type: application/json")
    @POST("/identity/signup")
    suspend fun registerAsync(
        @Body credentials: RegisterRequest
    )
}