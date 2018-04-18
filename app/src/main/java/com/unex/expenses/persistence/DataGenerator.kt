package com.unex.expenses.persistence

import com.unex.expenses.SpendingList
import com.unex.expenses.models.SpendingEntity
import java.util.*

object DataGenerator {

    private fun getDate(year: Int, month: Int, dayOfMonth: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun generateSpendings(): SpendingList {
        return listOf(
                SpendingEntity(1L, 200.0, getDate(2018, 1, 2), emptyList(), null),
                SpendingEntity(2L, 400.0, getDate(2018, 1, 3), emptyList(), null),
                SpendingEntity(3L, 350.5, getDate(2018, 1, 4), emptyList(), null)
        )
    }
}