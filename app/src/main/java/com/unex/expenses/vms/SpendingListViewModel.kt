package com.unex.expenses.vms

import android.app.Application
import android.arch.lifecycle.*
import android.os.AsyncTask
import com.unex.expenses.SpendingList
import com.unex.expenses.TagSet
import com.unex.expenses.models.SpendingsQuery
import com.unex.expenses.persistence.Database

class SpendingListViewModel(app: Application) : AndroidViewModel(app) {

    private val tagsObs: MutableLiveData<TagSet> = MutableLiveData()
    private val spendingsQueryObs: MediatorLiveData<SpendingsQuery> = MediatorLiveData()

    init {
        spendingsQueryObs.value = SpendingsQuery(listOf(), setOf())
        spendingsQueryObs.addSource(Database.get().spendingDao().retrieve(), { spendings ->
            spendings?.let {
                val tags = spendingsQueryObs.value!!.tags
                spendingsQueryObs.postValue(SpendingsQuery(it, tags))
            }
        })
        spendingsQueryObs.addSource(tagsObs, { tags ->
            tags?.let {
                val spendings = spendingsQueryObs.value!!.spendings
                spendingsQueryObs.postValue(SpendingsQuery(spendings, it))
            }
        })
    }

    fun getSpendings(): LiveData<SpendingList> = Transformations.map(spendingsQueryObs, { query ->
        query.spendings.filter {
            query.tags.isEmpty() || it.tags.intersect(query.tags).isNotEmpty()
        }
    })

    fun setSelectedTags(tags: TagSet) {
        tagsObs.postValue(tags)
    }

    fun deleteSpending(spendingId: Long) {
        val spending = spendingsQueryObs.value?.spendings?.find { spendingId == it.id }
        spending?.let {
            AsyncTask.execute { Database.get().spendingDao().delete(it) }
        }
    }
}
