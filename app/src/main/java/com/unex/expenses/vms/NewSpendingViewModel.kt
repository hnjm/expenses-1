package com.unex.expenses.vms

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.unex.expenses.models.Spending
import com.unex.expenses.repositories.SpendingRepository

class NewSpendingViewModel(application: Application) : AndroidViewModel(application) {

    fun addSpending(spending: Spending) {
        SpendingRepository.get().addSpending(spending)
    }
}