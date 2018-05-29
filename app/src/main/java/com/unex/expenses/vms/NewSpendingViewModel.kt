package com.unex.expenses.vms

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.unex.expenses.models.Spending
import com.unex.expenses.repositories.SpendingRepository
import java.util.*

class NewSpendingViewModel(app: Application) : AndroidViewModel(app) {

    var date: Date = Date()

    fun addSpending(spending: Spending) {
        SpendingRepository.addSpending(spending)
    }
}
