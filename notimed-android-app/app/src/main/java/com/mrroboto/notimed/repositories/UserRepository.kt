package com.mrroboto.notimed.repositories

import androidx.lifecycle.LiveData
import com.mrroboto.notimed.data.room.dao.UserDao
import com.mrroboto.notimed.models.User

class UserRepository(private val UserDao: UserDao) {
    suspend fun deleteUser(user: User) {
        UserDao.deleteUser(user)
    }

    suspend fun getInfoUser(email: String) {
        UserDao.getUserInfo(email)
    }

    suspend fun addUser(user: User){
        UserDao.insertUser(user)
    }
}