package com.unex.expenses.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import com.unex.expenses.DEFAULT_REMINDER_ENABLED
import com.unex.expenses.KEY_REMINDER_ENABLED
import com.unex.expenses.KEY_REMINDER_TIME
import com.unex.expenses.R
import com.unex.expenses.models.Notifications

class SettingsFragment : PreferenceFragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        addPreferencesFromResource(R.xml.preferences)
        with(PreferenceManager.getDefaultSharedPreferences(activity)) {
            registerOnSharedPreferenceChangeListener(this@SettingsFragment)
        }
    }

    override fun onSharedPreferenceChanged(preferences: SharedPreferences, key: String) {
        when (key) {
            KEY_REMINDER_ENABLED -> {
                if (preferences.getBoolean(KEY_REMINDER_ENABLED, DEFAULT_REMINDER_ENABLED)) {
                    Notifications.scheduleAlarm(activity)
                } else {
                    Notifications.cancelAlarm(activity)
                }
            }
            KEY_REMINDER_TIME -> {
                Notifications.scheduleAlarm(activity)
            }
        }
    }
}
