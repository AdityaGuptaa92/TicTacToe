package com.aditya.tictactoe.homepage

import android.content.Context
import android.content.SharedPreferences

class SharedPrefForNightMode(val context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("nightMode", Context.MODE_PRIVATE)

    fun setNightModeState(state: Boolean) {
        val editor = sharedPref.edit()
        editor.putBoolean("NightMode",state)
        editor.apply()
    }

    fun loadNightModeState(): Boolean {
        return sharedPref.getBoolean("NightMode",false)
    }

}