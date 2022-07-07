package com.mrroboto.notimed.network.services

import com.mrroboto.notimed.network.responses.identity.*
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

    @GET("/identity/whoami")
    suspend fun whoamiAsync() : WhoamiResponse

    @Headers("Content-Type: application/json")
    @PATCH("/identity/recover-password")
    suspend fun recoverPassword(@Body credentials: RecoverRequest)

    @PATCH("/users/{idUser}")
    suspend fun updateUser(@Path("idUser") idUser: String, @Body credentials: UpdateUserRequest)
}