package com.unex.expenses.models

import java.util.*

object Helper {

    private fun getDate(year: Int, month: Int, dayOfMonth: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun getDateString(date: Date): String {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return "$day/$month/$year"
    }

    fun createSpending(
            amount: String,
            date: String,
            tags: String,
            description: String
    ): Spending {
        val dateValue = getDate(
                date.split("/")[2].toInt(),
                date.split("/")[1].toInt(),
                date.split("/")[0].toInt()
        )
        return Spending(
                amount.toDouble(),
                dateValue,
                tags.split("-").map { it.trim() },
                if (description.isEmpty()) null else description
        )
    }
}