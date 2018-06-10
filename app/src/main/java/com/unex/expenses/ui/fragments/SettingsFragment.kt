package com.unex.expenses.ui.fragments

import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.SwitchPreference
import com.unex.expenses.KEY_REMINDER_ENABLED
import com.unex.expenses.KEY_REMINDER_TIME
import com.unex.expenses.R
import com.unex.expenses.dialogs.TimePreference
import com.unex.expenses.models.Notifications

class SettingsFragment : PreferenceFragment() {

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        addPreferencesFromResource(R.xml.preferences)

        val notificationRemember = findPreference(KEY_REMINDER_ENABLED) as SwitchPreference
        val notificationHour = findPreference(KEY_REMINDER_TIME) as TimePreference

        notificationRemember.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _: Preference, value: Any? ->
                    notificationHour.isEnabled = value as Boolean
                    if (value) {
                        Notifications.scheduleNewAlarm(context)
                    } else {
                        Notifications.cancelRunningAlarm(context)
                    }
                    true
                }
        notificationHour.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _: Preference, _: Any? ->
                    Notifications.scheduleNewAlarm(context)
                    true
                }
    }
}
