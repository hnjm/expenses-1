package com.unex.expenses.vms

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import com.unex.expenses.SpendingList
import com.unex.expenses.repositories.SpendingRepository

class SpendingListViewModel(application: Application) : AndroidViewModel(application) {

    private val spendingsObs: MediatorLiveData<SpendingList> = MediatorLiveData()

    init {
        spendingsObs.addSource(SpendingRepository.get().getSpendings(), spendingsObs::setValue)
    }

    fun getSpendings() = spendingsObs
}