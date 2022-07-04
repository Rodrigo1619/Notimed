package com.mrroboto.notimed.network

import com.mrroboto.notimed.network.responses.identity.LoginRequest
import com.mrroboto.notimed.network.responses.identity.LoginResponse
import com.mrroboto.notimed.network.responses.identity.RegisterRequest
import com.mrroboto.notimed.network.responses.identity.WhoamiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

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


    @GET("/identity/whoami")
    suspend fun whoamiAsync() : WhoamiResponse
}