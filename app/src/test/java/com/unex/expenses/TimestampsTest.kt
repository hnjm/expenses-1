package com.unex.expenses

import com.unex.expenses.models.Dates
import com.unex.expenses.models.Moment
import com.unex.expenses.models.Timestamps
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class TimestampsTest {

    private val date = Dates.createDate(2018, 5, 2)

    @Test
    fun startOfDay() {
        val calendar = GregorianCalendar()
        calendar.timeInMillis = Timestamps.startOf(Moment.DAY, date)
        assertEquals(5, calendar.get(Calendar.MONTH))
        assertEquals(2, calendar.get(Calendar.DAY_OF_MONTH))
        assertEquals(0, calendar.get(Calendar.HOUR_OF_DAY))
        assertEquals(0, calendar.get(Calendar.MINUTE))
        assertEquals(0, calendar.get(Calendar.SECOND))
        assertEquals(0, calendar.get(Calendar.MILLISECOND))
    }

    @Test
    fun startOfWeek() {
        val calendar = GregorianCalendar()
        calendar.timeInMillis = Timestamps.startOf(Moment.WEEK, date)
        assertEquals(4, calendar.get(Calendar.MONTH))
        assertEquals(28, calendar.get(Calendar.DAY_OF_MONTH))
        assertEquals(0, calendar.get(Calendar.HOUR_OF_DAY))
        assertEquals(0, calendar.get(Calendar.MINUTE))
        assertEquals(0, calendar.get(Calendar.SECOND))
        assertEquals(0, calendar.get(Calendar.MILLISECOND))
    }

    @Test
    fun startOfMonth() {
        val calendar = GregorianCalendar()
        calendar.timeInMillis = Timestamps.startOf(Moment.MONTH, date)
        assertEquals(5, calendar.get(Calendar.MONTH))
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH))
        assertEquals(0, calendar.get(Calendar.HOUR_OF_DAY))
        assertEquals(0, calendar.get(Calendar.MINUTE))
        assertEquals(0, calendar.get(Calendar.SECOND))
        assertEquals(0, calendar.get(Calendar.MILLISECOND))
    }
}
