package com.unex.expenses.vms

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.preference.PreferenceManager
import com.unex.expenses.DEFAULT_DAILY_LIMIT
import com.unex.expenses.DEFAULT_MONTHLY_LIMIT
import com.unex.expenses.KEY_DAILY_LIMIT
import com.unex.expenses.KEY_MONTHLY_LIMIT
import com.unex.expenses.models.Moment
import com.unex.expenses.models.Timestamps
import com.unex.expenses.persistence.Database

class SpentTabViewModel(val app: Application) : AndroidViewModel(app) {

    fun getDailyLimit(): Int {
        val preferences = PreferenceManager.getDefaultSharedPreferences(app)
        return preferences.getString(KEY_DAILY_LIMIT, DEFAULT_DAILY_LIMIT).toInt()
    }

    fun getMonthlyLimit(): Int {
        val preferences = PreferenceManager.getDefaultSharedPreferences(app)
        return preferences.getString(KEY_MONTHLY_LIMIT, DEFAULT_MONTHLY_LIMIT).toInt()
    }

    val daySpendingsObs = Database.get().spendingDao().retrieve(Timestamps.startOf(Moment.DAY))
    val monthSpendingsObs = Database.get().spendingDao().retrieve(Timestamps.startOf(Moment.MONTH))
}
