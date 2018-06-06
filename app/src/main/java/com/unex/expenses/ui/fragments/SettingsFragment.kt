package com.unex.expenses.ui.fragments

import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.SwitchPreference
import com.unex.expenses.R
import com.unex.expenses.utils.NotificationUtils
import com.unex.expenses.dialogs.TimePreference

class SettingsFragment : PreferenceFragment() {

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        addPreferencesFromResource(R.xml.preferences)

        var notificationRemember = findPreference("notification_remember") as SwitchPreference
        var notificationHour = findPreference("notification_hour") as TimePreference

        notificationRemember.setOnPreferenceChangeListener(Preference.OnPreferenceChangeListener{ preference: Preference, newValue: Any? ->
            notificationHour.setEnabled(newValue as Boolean)
            if (newValue) {
                NotificationUtils.scheduleAlarm(context)
            } else {
                NotificationUtils.cancelAlarms(context)
            }
            true
        })

        notificationHour.setOnPreferenceChangeListener(Preference.OnPreferenceChangeListener{ preference: Preference, newValue: Any? ->
            NotificationUtils.scheduleAlarm(context)
            true
        })


    }


}

