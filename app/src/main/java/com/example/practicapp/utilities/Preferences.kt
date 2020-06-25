package com.example.practicapp.utilities

import android.content.Context
import com.example.practicapp.models.User

object Preferences {

    fun saveUser(context: Context, user: User?) {
        val sharedPref = context.getSharedPreferences("reference", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("user_name", user?.name)
        editor.putString("user_email", user?.email)
        editor.apply()
    }

    fun loadUser(context: Context): User {
        val sharedPref = context.getSharedPreferences("reference", Context.MODE_PRIVATE)
        return User(sharedPref.getString("user_name", "")!!, sharedPref.getString("user_email", "")!!)

    }

    fun clearData(context: Context) {
        val sharedPref = context.getSharedPreferences("reference", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
}