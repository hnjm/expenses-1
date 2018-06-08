package com.unex.expenses.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.unex.expenses.ui.fragments.SettingsFragment

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, SettingsFragment())
                .commit()
    }
}
