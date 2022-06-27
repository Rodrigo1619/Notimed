package com.mrroboto.notimed.repositories

import com.mrroboto.notimed.data.dao.UserDao
import com.mrroboto.notimed.data.models.User
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.network.IdentityService
import com.mrroboto.notimed.network.dto.LoginRequest
import com.mrroboto.notimed.network.dto.LoginResponse
import retrofit2.HttpException

class UserRepository(private val UserDao: UserDao, private val api: IdentityService) {
    suspend fun deleteUser(user: User) {
        UserDao.deleteUser(user)
    }

    suspend fun getInfoUser(email: String) {
        UserDao.getUserInfo(email)
    }

    suspend fun addUser(user: User){
        UserDao.insertUser(user)
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