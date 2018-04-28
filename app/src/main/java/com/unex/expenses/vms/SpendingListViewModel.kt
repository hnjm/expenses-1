package com.unex.expenses.vms

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import com.unex.expenses.SpendingList
import com.unex.expenses.repositories.SpendingRepository

class SpendingListViewModel(application: Application) : AndroidViewModel(application) {

    private var storedSpendings: SpendingList = emptyList()
    private var selectedTags: Set<String> = emptySet()

    private val spendingsObs: MediatorLiveData<SpendingList> = MediatorLiveData()

    private fun getFilteredSpendings() = storedSpendings.filter {
        selectedTags.isEmpty() || it.getTags().intersect(selectedTags).isNotEmpty()
    }

    init {
        spendingsObs.addSource(SpendingRepository.get().getSpendings(), {
            it?.let {
                storedSpendings = it
                val spendings = getFilteredSpendings()
                spendingsObs.postValue(spendings)
            }
        })
    }

    fun getSpendings() = spendingsObs

    fun setSelectedTags(tags: Set<String>) {
        selectedTags = tags
        val spendings = getFilteredSpendings()
        spendingsObs.postValue(spendings)
    }
}
