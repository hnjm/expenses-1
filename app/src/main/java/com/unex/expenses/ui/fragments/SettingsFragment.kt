package com.unex.expenses.ui.fragments

import android.os.Bundle
import android.preference.PreferenceFragment
import com.unex.expenses.R

class SettingsFragment : PreferenceFragment() {

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        addPreferencesFromResource(R.xml.preferences)
    }
}
