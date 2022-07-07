package com.mrroboto.notimed.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders_table")
data class Reminder(
    @PrimaryKey
    @ColumnInfo("_id") val _id: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("repeatEvery") val repeatEvery: Int,
    @ColumnInfo("hour") val hour: String,
    @ColumnInfo("dose") val dose: Int,
    @ColumnInfo("startDate") val startDate: String,
    @ColumnInfo("endDate") val endDate: String,
    @ColumnInfo("foodOption") val foodOption: Boolean,
    @ColumnInfo("user") val user: String
)