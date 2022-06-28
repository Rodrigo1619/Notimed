package com.mrroboto.notimed.repositories

import com.google.gson.annotations.SerializedName
import com.mrroboto.notimed.data.dao.UserDao
import com.mrroboto.notimed.data.models.User
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.IdentityService
import com.mrroboto.notimed.network.responses.identity.LoginRequest
import com.mrroboto.notimed.network.responses.identity.RegisterRequest
import retrofit2.HttpException

class UserRepository(private val UserDao: UserDao, private val api: IdentityService) {
    suspend fun deleteUser(user: User) {
        UserDao.deleteUser(user)
    }

    suspend fun getInfoUser(email: String) {
        UserDao.getUserInfo(email)
    }

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
}