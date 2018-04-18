package com.unex.expenses.persistence

import android.arch.persistence.room.TypeConverter

class ListConverter {
    @TypeConverter
    fun to(tags: String?): List<String>? {
        return tags?.split("-")
    }

    @TypeConverter
    fun from(tags: List<String>?): String? {
        return tags?.joinToString("-")
    }
}