package com.uchiha.ets_pemmob_kelompok5

import android.content.Context
import android.content.SharedPreferences

class UserPrefs(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUser(username: String, password: String, fullName: String) {
        sharedPreferences.edit().apply {
            putString("username", username)
            putString("password", password)
            putString("fullName", fullName)
            apply()
        }
    }

    fun getUser(): Triple<String?, String?, String?> {
        val username = sharedPreferences.getString("username", null)
        val password = sharedPreferences.getString("password", null)
        val fullName = sharedPreferences.getString("fullName", null)
        return Triple(username, password, fullName)
    }
}