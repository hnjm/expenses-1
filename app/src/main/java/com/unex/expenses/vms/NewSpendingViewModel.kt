package com.unex.expenses.vms

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.unex.expenses.TagSet
import com.unex.expenses.models.DateHelper
import com.unex.expenses.models.Spending
import com.unex.expenses.repositories.SpendingRepository
import java.util.*

class NewSpendingViewModel(app: Application) : AndroidViewModel(app) {

    val dateObs: MutableLiveData<Date> = MutableLiveData()
    val tagsObs: MutableLiveData<TagSet> = MutableLiveData()

    init {
        dateObs.value = Date()
        tagsObs.value = setOf()
    }

    fun addSpending(spending: Spending) {
        SpendingRepository.addSpending(spending)
    }

    fun getDate(): Date {
        return dateObs.value ?: Date()
    }

    fun getTags(): TagSet {
        return tagsObs.value ?: setOf()
    }

    fun setDate(year: Int, month: Int, day: Int) {
        dateObs.value = DateHelper.createDate(year, month, day)
    }

    fun setTags(newTags: TagSet) {
        tagsObs.value = newTags
    }
}
