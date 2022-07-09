package com.mrroboto.notimed.data.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {
    @Query("DELETE FROM reminders_table")
    suspend fun deleteAllReminderInfo()

    @Query("DELETE FROM appointments_table")
    suspend fun deleteAllAppointmentsInfo()

    @Query("DELETE FROM contacts_table")
    suspend fun deleteAllContactsInfo()
}