package com.unex.expenses.ui.fragments

import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.SwitchPreference
import com.unex.expenses.KEY_REMINDER_ENABLED
import com.unex.expenses.KEY_REMINDER_TIME
import com.unex.expenses.R
import com.unex.expenses.models.Notifications

class SettingsFragment : PreferenceFragment() {

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        addPreferencesFromResource(R.xml.preferences)

        val reminderEnabled = findPreference(KEY_REMINDER_ENABLED) as SwitchPreference
        val reminderHour = findPreference(KEY_REMINDER_TIME) as ListPreference

        reminderEnabled.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _: Preference, value: Any? ->
                    if (value as Boolean) {
                        Notifications.scheduleAlarm(context)
                    } else {
                        Notifications.cancelRunningAlarm(context)
                    }
                    true
                }
        reminderHour.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _: Preference, _: Any? ->
                    Notifications.scheduleAlarm(context)
                    true
                }
    }
}
