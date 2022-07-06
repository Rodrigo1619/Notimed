package com.mrroboto.notimed.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey @ColumnInfo(name = "_id") val id: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "lastname") val lastName: String,
    @ColumnInfo(name = "birthday") val birthday: String,
    @ColumnInfo(name = "gender") val gender: String
    )
