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

    val dateData: MutableLiveData<Date> = MutableLiveData()
    val tagsData: MutableLiveData<TagSet> = MutableLiveData()

    init {
        dateData.value = Date()
        tagsData.value = setOf()
    }

    fun addSpending(spending: Spending) {
        SpendingRepository.addSpending(spending)
    }

    fun getDate(): Date {
        return dateData.value ?: Date()
    }

    fun getTags(): TagSet {
        return tagsData.value ?: setOf()
    }

    fun setDate(year: Int, month: Int, day: Int) {
        dateData.value = DateHelper.createDate(year, month, day)
    }

    fun setTags(newTags: TagSet) {
        tagsData.value = newTags
    }
}
