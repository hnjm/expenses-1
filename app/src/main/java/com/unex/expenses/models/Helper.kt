package com.unex.expenses.models

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Helper {

    fun getCurrentDay(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun getCurrentMonth(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.MONTH)
    }

    fun getCurrentYear(): Int {
        val calendar = GregorianCalendar()
        return calendar.get(Calendar.YEAR)
    }

    fun getOneWeekAgoTimestamp(date: Date = Date()): Long {
        val calendar = GregorianCalendar()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        return calendar.timeInMillis
    }

    fun getOneMonthAgoTimestamp(date: Date = Date()): Long {
        val calendar = GregorianCalendar()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_YEAR, -30)
        return calendar.timeInMillis
    }

    fun createDate(year: Int, month: Int, dayOfMonth: Int): Date {
        val calendar = GregorianCalendar()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun getDateString(date: Date): String = SimpleDateFormat
            .getDateInstance(DateFormat.LONG)
            .format(date)

    fun createSpending(
            amount: String,
            date: Date,
            tags: String,
            description: String
    ): Spending {
        return Spending(
                amount.toDouble(),
                date,
                tags.split(", ").toSet(),
                if (description.isEmpty()) null else description
        )
    }
}
