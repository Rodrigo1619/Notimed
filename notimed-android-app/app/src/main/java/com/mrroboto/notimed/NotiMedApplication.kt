package com.mrroboto.notimed

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mrroboto.notimed.data.AppDatabase
import com.mrroboto.notimed.network.RetrofitInstance
import com.mrroboto.notimed.repositories.ReminderRepository
import com.mrroboto.notimed.repositories.UserRepository

class NotiMedApplication : Application() {

    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("NotiMed", Context.MODE_PRIVATE)
    }

    private val database by lazy {
        AppDatabase.getInstance(this)
    }

    private fun getIdentityService() = with(RetrofitInstance) {
        setToken(getToken())
        getIdentityServices()
    }

    private fun getReminderService() = with(RetrofitInstance) {
        this.getReminderServices()
    }

    fun getUserRepository() = UserRepository(getIdentityService(), database)

    fun getReminderRepository() = ReminderRepository(getReminderService(), database, getId())

    private fun getToken(): String = prefs.getString(USER_TOKEN, "")!!
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    private fun getId() : String = prefs.getString(USER_id, "")!!

    fun saveID(id: String) {
        val editor = prefs.edit()
        editor.putString(USER_id, id)
        editor.apply()
    }

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_id = "user_id"
    }

}