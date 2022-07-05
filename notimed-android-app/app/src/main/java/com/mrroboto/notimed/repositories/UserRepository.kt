package com.mrroboto.notimed.repositories

import com.mrroboto.notimed.data.AppDatabase
import com.mrroboto.notimed.data.models.User
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.responses.identity.LoginRequest
import com.mrroboto.notimed.network.responses.identity.RecoverRequest
import com.mrroboto.notimed.network.responses.identity.RegisterRequest
import com.mrroboto.notimed.network.services.IdentityService
import retrofit2.HttpException

class UserRepository(private val api: IdentityService, database: AppDatabase) {

    private val user = database.userDao()

    suspend fun register(
        name: String,
        lastName: String,
        email: String,
        password: String,
        birthday: String,
        gender: String
    ): ApiResponse<Any> {
        return try {
            val response = api.registerAsync(
                RegisterRequest(
                    name,
                    lastName,
                    email,
                    password,
                    birthday,
                    gender,
                )
            )
            ApiResponse.Success(response)
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.response().toString())
        }
    }

    suspend fun login(email: String, password: String): ApiResponse<Any> {
        return try {
            val response = api.loginAsync(LoginRequest(email, password))
            ApiResponse.Success(response.token)
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.response().toString())
        }
    }


    suspend fun whoami(): ApiResponse<Any> {
        return try {
            val response = api.whoamiAsync()
            user.insertUserInfo(
                User(
                    response.content._id,
                    response.content.email,
                    response.content.name,
                    response.content.lastName,
                    response.content.birthday,
                    response.content.gender
                )
            )
            ApiResponse.Success (response.content.name)
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.response().toString())
        }
    }

    suspend fun recoverPassword(email: String): ApiResponse<Any> {
        return try {
            val response = api.recoverPassword(RecoverRequest(email))
            ApiResponse.Success(response)
        } catch (error: HttpException) {
            ApiResponse.Failure(error.code(), error.message())
        }
    }
}