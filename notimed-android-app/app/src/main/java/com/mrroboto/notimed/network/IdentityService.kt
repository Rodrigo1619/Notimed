package com.mrroboto.notimed.network

import com.mrroboto.notimed.network.dto.LoginRequest
import com.mrroboto.notimed.network.dto.LoginResponse
import com.mrroboto.notimed.network.dto.UserDto
import retrofit2.http.*

interface IdentityService {
    @Headers("Content-Type: application/json")
    @POST("/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse
}