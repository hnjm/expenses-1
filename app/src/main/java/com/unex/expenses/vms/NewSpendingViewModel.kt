package com.unex.expenses.vms

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.unex.expenses.TagSet
import com.unex.expenses.models.Dates
import com.unex.expenses.persistence.Database
import com.unex.expenses.persistence.entities.Spending
import java.util.*

class NewSpendingViewModel(app: Application) : AndroidViewModel(app) {

    val dateObs: MutableLiveData<Date> = MutableLiveData()
    val tagsObs: MutableLiveData<TagSet> = MutableLiveData()

    init {
        dateObs.value = Date()
        tagsObs.value = setOf()
    }

    fun addSpending(spending: Spending) {
        Database.get().spendingDao().insert(spending)
    }

    fun getDate(): Date {
        return dateObs.value ?: Date()
    }

    fun getTags(): TagSet {
        return tagsObs.value ?: setOf()
    }

    fun setDate(year: Int, month: Int, day: Int) {
        dateObs.value = Dates.createDate(year, month, day)
    }

    fun setTags(newTags: TagSet) {
        tagsObs.value = newTags
    }
}
