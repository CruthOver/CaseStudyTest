package com.example.mobileapptest.helpers

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs (context: Context) {
    private val userBalance = "BALANCE_USER"
    private val preferences: SharedPreferences = context.getSharedPreferences(userBalance, Context.MODE_PRIVATE)

    var userBalancePref: Int
        get() = preferences.getInt(userBalance, 0)
        set(value) = preferences.edit().putInt(userBalance, value).apply()

}