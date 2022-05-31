package com.netsoft.android.timer.common

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManager @Inject constructor(context: Context) {

    companion object {
        private const val PREF_PACKAGE_NAME = "com.hubstaff.challenge.preferences"
        private const val PREF_KEY_TIMER = "timer"
        private const val PREF_KEY_TEMP_TIMER = "temp_timer"
        private const val PREF_KEY_TIMER_RUNNING = "timer_running"
    }

    private val pref: SharedPreferences =
        context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)

    var timeInMillis: Long
        get() = pref.getLong(PREF_KEY_TIMER, 0)
        set(timer) = pref.edit().putLong(PREF_KEY_TIMER, timer).apply()

    var tempTimeInMillis: Long
        get() = pref.getLong(PREF_KEY_TEMP_TIMER, 0)
        set(temp) = pref.edit().putLong(PREF_KEY_TEMP_TIMER, temp).apply()

    var isTimerRunning: Boolean
        get() = pref.getBoolean(PREF_KEY_TIMER_RUNNING, false)
        set(isTimerRunning) = pref.edit().putBoolean(PREF_KEY_TIMER_RUNNING, isTimerRunning).apply()
}