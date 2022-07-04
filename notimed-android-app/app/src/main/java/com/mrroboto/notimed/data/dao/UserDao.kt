package com.mrroboto.notimed.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mrroboto.notimed.data.models.User

@Dao
interface UserDao {
    // Insertar usuario cuando se inicie sesion y sea exitoso
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(user: User)
}