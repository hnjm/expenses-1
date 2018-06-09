package com.unex.expenses.models

import java.util.*

object Timestamps {

    fun startOf(start: Moment, date: Date = Date()): Long {
        val calendar = GregorianCalendar()
        return with(calendar) {
            time = date
            when (start) {
                Moment.DAY -> startOfDay(this)
                Moment.WEEK -> startOfWeek(this)
                Moment.MONTH -> startOfMonth(this)
            }
            timeInMillis
        }
    }

    private fun startOfDay(calendar: Calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
    }

    private fun startOfWeek(calendar: Calendar) {
        startOfDay(calendar)
        val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
        calendar.add(Calendar.DAY_OF_YEAR, when (weekDay) {
            Calendar.TUESDAY -> -1
            Calendar.WEDNESDAY -> -2
            Calendar.THURSDAY -> -3
            Calendar.FRIDAY -> -4
            Calendar.SATURDAY -> -5
            Calendar.SUNDAY -> -6
            else -> 0
        })
    }

    private fun startOfMonth(calendar: Calendar) {
        startOfDay(calendar)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        calendar.add(Calendar.DAY_OF_YEAR, -day + 1)
    }
}
