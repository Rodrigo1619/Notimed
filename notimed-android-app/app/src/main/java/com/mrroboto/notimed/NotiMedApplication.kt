package com.mrroboto.notimed

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mrroboto.notimed.data.AppDatabase
import com.mrroboto.notimed.network.RetrofitInstance
import com.mrroboto.notimed.repositories.UserRepository

class NotiMedApplication : Application() {

    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("NotiMed", Context.MODE_PRIVATE)
    }

    private val database by lazy {
        AppDatabase.getInstance(this)
    }

    private fun getApiService() = with(RetrofitInstance) {
        getIdentityServices()
    }

    fun getUserRepository() = with(database) {
        UserRepository(userDao(), getApiService())
    }
}