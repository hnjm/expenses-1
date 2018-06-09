package com.unex.expenses.vms

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.os.AsyncTask
import com.unex.expenses.SpendingList
import com.unex.expenses.persistence.Database

class SpendingListViewModel(app: Application) : AndroidViewModel(app) {

    private var storedSpendings: SpendingList = emptyList()
    private var selectedTags: Set<String> = emptySet()

    val spendingsObs: MediatorLiveData<SpendingList> = MediatorLiveData()

    private fun getFilteredSpendings() = storedSpendings.filter {
        selectedTags.isEmpty() || it.getTags().intersect(selectedTags).isNotEmpty()
    }

    init {
        spendingsObs.addSource(Database.get().spendingDao().retrieve(), {
            it?.let {
                storedSpendings = it
                val spendings = getFilteredSpendings()
                spendingsObs.postValue(spendings)
            }
        })
    }

    fun setSelectedTags(tags: Set<String>) {
        selectedTags = tags
        val spendings = getFilteredSpendings()
        spendingsObs.postValue(spendings)
    }

    fun deleteSpending(spendingId: Long) {
        val spending = storedSpendings.find { spendingId == it.getId() }
        spending?.let {
            AsyncTask.execute { Database.get().spendingDao().delete(it) }
        }
    }
}
