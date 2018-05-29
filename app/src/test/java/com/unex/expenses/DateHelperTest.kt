package com.unex.expenses

import com.unex.expenses.models.DateHelper
import org.junit.Assert.assertEquals
import org.junit.Test

class DateHelperTest {

    @Test
    fun getOneWeekAgoTimestamp() {
        val date = DateHelper.createDate(2010, 9, 20)
        val timestamp = DateHelper.oneWeekAgoTimestamp(date)
        assertEquals(1286938800000L, timestamp)
    }

    @Test
    fun getOneMonthAgoTimestamp() {
        val date = DateHelper.createDate(2010, 9, 20)
        val timestamp = DateHelper.oneMonthAgoTimestamp(date)
        assertEquals(1284951600000, timestamp)
    }
}
