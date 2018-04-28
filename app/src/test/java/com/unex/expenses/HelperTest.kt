package com.unex.expenses

import com.unex.expenses.models.Helper
import org.junit.Assert.assertEquals
import org.junit.Test

class HelperTest {

    @Test
    fun getOneWeekAgoTimestamp() {
        val date = Helper.createDate(2010, 9, 20)
        val timestamp = Helper.getOneWeekAgoTimestamp(date)
        assertEquals(1286938800000L, timestamp)
    }

    @Test
    fun getOneMonthAgoTimestamp() {
        val date = Helper.createDate(2010, 9, 20)
        val timestamp = Helper.getOneMonthAgoTimestamp(date)
        assertEquals(1284951600000, timestamp)
    }
}
