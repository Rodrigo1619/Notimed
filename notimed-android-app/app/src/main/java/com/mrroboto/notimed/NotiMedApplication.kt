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
        setToken(getToken())
        getIdentityServices()
    }

    private fun getToken(): String = prefs.getString(USER_TOKEN, "")!!
    fun isUserLogin() = getToken() != ""
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun getUserRepository() = with(database) {
        UserRepository(userDao(), getApiService())
    }
}