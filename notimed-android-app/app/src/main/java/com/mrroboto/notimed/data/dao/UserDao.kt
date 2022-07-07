package com.mrroboto.notimed.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrroboto.notimed.data.models.User

@Dao
interface UserDao {
    // Insertar usuario cuando se inicie sesion y sea exitoso
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(user: User)

    @Query("SELECT _id FROM user_table WHERE _id LIKE :id")
    suspend fun getIdUser(id: String) : String
}