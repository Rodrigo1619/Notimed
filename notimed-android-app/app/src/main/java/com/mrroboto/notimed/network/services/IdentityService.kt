package com.mrroboto.notimed.network.services

import com.mrroboto.notimed.network.responses.identity.*
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

    @Headers("Content-Type: application/json")
    @POST("/identity/recover-password")
    suspend fun recoverPassword(@Body credentials: RecoverRequest)
}