package com.unex.expenses.persistence

import android.arch.persistence.room.TypeConverter

class TagsConverter {
    @TypeConverter
    fun to(tags: String?): Set<String>? {
        return HashSet<String>(tags?.split("-"))
    }

    @TypeConverter
    fun from(tags: Set<String>?): String? {
        return tags?.joinToString("-")
    }
}