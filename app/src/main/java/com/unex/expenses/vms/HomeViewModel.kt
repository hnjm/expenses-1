package com.unex.expenses.vms

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.preference.PreferenceManager
import com.unex.expenses.KEY_DAILY_LIMIT
import com.unex.expenses.models.Moment
import com.unex.expenses.models.Timestamps
import com.unex.expenses.persistence.Database

class HomeViewModel(val app: Application) : AndroidViewModel(app) {

    fun getDailyLimit(): Int {
        val preferences = PreferenceManager.getDefaultSharedPreferences(app)
        return preferences.getString(KEY_DAILY_LIMIT, "0").toInt()
    }

    val spendingsObs = Database.get().spendingDao().retrieve(Timestamps.startOf(Moment.DAY))
}
