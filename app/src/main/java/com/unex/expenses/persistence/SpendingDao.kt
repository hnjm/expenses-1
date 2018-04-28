package com.unex.expenses.persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.unex.expenses.SpendingList
import com.unex.expenses.models.Spending

@Dao
interface SpendingDao {

    @Query("SELECT * FROM spendings")
    fun getAll(): LiveData<SpendingList>

    @Query("SELECT * FROM spendings WHERE date > :limit")
    fun getFiltered(limit: Long): LiveData<SpendingList>

    @Insert()
    fun insert(spending: Spending)

    @Delete()
    fun delete(spending: Spending)
}
