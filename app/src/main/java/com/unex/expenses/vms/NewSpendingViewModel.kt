package com.unex.expenses.vms

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.unex.expenses.models.Spending
import com.unex.expenses.repositories.SpendingRepository
import java.util.*

class NewSpendingViewModel(application: Application) : AndroidViewModel(application) {

    private var date: Date? = null

    fun addSpending(spending: Spending) {
        SpendingRepository.get().addSpending(spending)
    }

    fun getDate(): Date = date ?: Date()

    fun setDate(newDate: Date) {
        date = newDate
    }
}
