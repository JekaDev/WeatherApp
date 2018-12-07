package com.example.ebobrovnichiy.weatherapp.data.prefs

import android.content.Context
import android.content.SharedPreferences
import java.util.concurrent.TimeUnit

class AppPreferencesHelper constructor(context: Context) {

    companion object {
        const val SHARED_PREF = "sharedPref"
        const val UPDATE_PERIOD = "updatePeriod"
    }

    private val sharedPreference: SharedPreferences

    init {
        sharedPreference = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
    }

    fun setUpdatePeriodInterval(periodInterval: Int) {
        val prefsEditor = sharedPreference.edit()
        prefsEditor.putInt(UPDATE_PERIOD, periodInterval)
        prefsEditor.apply()
    }

    fun getUpdatePeriodInterval(): Int = sharedPreference.getInt(UPDATE_PERIOD, TimeUnit.MINUTES.toSeconds(30).toInt())
}