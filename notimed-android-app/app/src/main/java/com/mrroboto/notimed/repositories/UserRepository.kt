package com.mrroboto.notimed.repositories

import com.mrroboto.notimed.data.AppDatabase
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.responses.identity.*
import com.mrroboto.notimed.network.services.IdentityService
import retrofit2.HttpException

class UserRepository(private val api: IdentityService, database: AppDatabase) {

    private val userDao = database.userDao()
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
                    gender
                )
            )
            ApiResponse.Success(response)
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.message())
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


    suspend fun whoami(): ApiResponse<WhoamiResponse> {
        return try {
            val response = api.whoamiAsync()
            ApiResponse.Success(response)
        } catch (err: HttpException) {
            ApiResponse.Failure(err.code(), err.message())
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

    suspend fun updateUser(
        id: String,
        name: String,
        birthday: String,
        lastName: String,
        gender: String
    ): ApiResponse<Any> {
        return try {
            val response = api.updateUser(id, UpdateUserRequest(name, lastName, birthday, gender))
            ApiResponse.Success(response)
        } catch (error: HttpException) {
            ApiResponse.Failure(error.code(), error.message())
        }
    }

    suspend fun deleteReminders() {
        userDao.deleteAllReminderInfo()
    }
}