package com.unex.expenses.persistence.converters

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun to(timestamp: Long): Date {
        return Date(timestamp)
    }

    @TypeConverter
    fun from(date: Date): Long {
        return date.time
    }
}
