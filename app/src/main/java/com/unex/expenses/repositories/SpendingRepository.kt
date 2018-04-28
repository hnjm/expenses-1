package com.unex.expenses.repositories

import android.arch.lifecycle.MediatorLiveData
import com.unex.expenses.SpendingList
import com.unex.expenses.models.Helper
import com.unex.expenses.models.Spending
import com.unex.expenses.persistence.Database

class SpendingRepository() {

    private val spendingsObs: MediatorLiveData<SpendingList> = MediatorLiveData()

    init {
        spendingsObs.addSource(
                Database.get().spendingDao().getFiltered(Helper.getOneMonthAgoTimestamp()),
                spendingsObs::postValue
        )
    }

    fun getSpendings() = spendingsObs

    fun addSpending(spending: Spending) = Database.get().spendingDao().insert(spending)

    fun deleteSpending(spending: Spending) = Database.get().spendingDao().delete(spending)

    companion object {
        private var instance: SpendingRepository? = null

        fun get(): SpendingRepository {
            return instance ?: SpendingRepository()
        }
    }
}
