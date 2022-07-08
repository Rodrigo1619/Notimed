package com.mrroboto.notimed.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mrroboto.notimed.network.responses.contact.Days
import java.util.*
@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey
    @ColumnInfo("_id") val id: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("phoneNumber") val phoneNumber: String,
    @ColumnInfo("adrress") val address: String,
    @ColumnInfo("specialization") val specialization: String,
    @ColumnInfo("startHour") val startHour: String,
    @ColumnInfo("endHour") val endHour: String,
    @ColumnInfo("days") val days: Days,
    @ColumnInfo("user") val user: String
)
