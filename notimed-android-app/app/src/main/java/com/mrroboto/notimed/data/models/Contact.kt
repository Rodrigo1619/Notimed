package com.mrroboto.notimed.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey
    @ColumnInfo("_id") val _id: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("phoneNumber") val phoneNumber: String,
    @ColumnInfo("address") val address: String,
    @ColumnInfo("specialization") val specialization: String,
    @ColumnInfo("startHour") val startHour: String,
    @ColumnInfo("endHour") val endHour: String,
    @ColumnInfo("user") val user: String
)
