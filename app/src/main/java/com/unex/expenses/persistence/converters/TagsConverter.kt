package com.unex.expenses.persistence.converters

import android.arch.persistence.room.TypeConverter

class TagsConverter {
    @TypeConverter
    fun to(tags: String?): Set<String>? {
        return tags?.split(".")?.toSet()
    }

    @TypeConverter
    fun from(tags: Set<String>?): String? {
        return tags?.joinToString(".")
    }
}
