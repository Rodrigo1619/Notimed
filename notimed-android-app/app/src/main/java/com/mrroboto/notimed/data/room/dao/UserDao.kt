package com.mrroboto.notimed.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mrroboto.notimed.models.User

@Dao
interface UserDao {
    // Insertar usuario cuando se inicie sesion
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE email LIKE :email")
    suspend fun readLoginData(email: String): User

    @Query("SELECT * FROM User WHERE email LIKE :email")
    suspend fun getUserInfo(email: String): User

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}