package com.mrroboto.notimed.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mrroboto.notimed.data.dao.ReminderDao
import com.mrroboto.notimed.data.dao.UserDao
import com.mrroboto.notimed.data.models.Reminder
import com.mrroboto.notimed.data.models.User

@Database(entities = [User::class, Reminder::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun reminderDao(): ReminderDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notimed_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}