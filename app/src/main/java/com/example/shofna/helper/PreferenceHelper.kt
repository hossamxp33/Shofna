package com.example.shofna.helper


import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.NullPointerException
import java.util.ArrayList

class PreferenceHelper(private val context: Context) {

    companion object {
        private var app_prefs: SharedPreferences? = null
        private const val app_ref = "userdetails"
        private const val CART_ARRAY = "CART_ARRAY"
        private const val IsEnabled = "IsEnabled"
        private val arrPackage: ArrayList<String> = ArrayList<String>()
        private const val Username = "username"


    }

    fun getUsername(): String? {
        return app_prefs!!.getString(Username, "")
    }

    fun setUsername(username: String?) {
        val edit = app_prefs!!.edit()
        edit.putString(Username, username)
        edit.apply()
    }

    init {
        try {
            app_prefs = context.getSharedPreferences(app_ref, Context.MODE_PRIVATE)
        } catch (e: NullPointerException) {
        }
    }
}