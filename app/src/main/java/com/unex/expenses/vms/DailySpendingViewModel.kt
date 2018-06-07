package com.unex.expenses.vms

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.preference.PreferenceManager
import com.unex.expenses.models.DateHelper
import com.unex.expenses.repositories.SpendingRepository

class DailySpendingViewModel(val app: Application) : AndroidViewModel(app) {

    fun getDailyLimit(): Int {
        val preferences = PreferenceManager.getDefaultSharedPreferences(app)
        return preferences.getString("daily_limit", "0").toInt()
    }

    val spendingsObs = SpendingRepository.getSpendings(DateHelper.startOfDayTimestamp())
}
