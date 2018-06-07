package com.unex.expenses.repositories

import com.unex.expenses.models.DateHelper
import com.unex.expenses.models.Spending
import com.unex.expenses.persistence.Database

object SpendingRepository {

    fun getSpendings(startDate: Long = DateHelper.oneMonthAgoTimestamp()) =
            Database.get().spendingDao().retrieve(startDate)

    fun addSpending(spending: Spending) =
            Database.get().spendingDao().insert(spending)

    fun deleteSpending(spending: Spending) =
            Database.get().spendingDao().delete(spending)
}
