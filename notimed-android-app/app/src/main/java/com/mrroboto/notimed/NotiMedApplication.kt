package com.mrroboto.notimed

import android.app.Application
import com.mrroboto.notimed.data.room.AppDatabase
import com.mrroboto.notimed.repositories.UserRepository

class NotiMedApplication : Application() {
    private val database by lazy {
        AppDatabase.getInstance(this)
    }

    fun getUserRepository() = with(database) {
        UserRepository(userDao())
    }
}