package com.unex.expenses.persistence.converters

import android.arch.persistence.room.TypeConverter
import com.unex.expenses.TagSet

class TagSetConverter {

    @TypeConverter
    fun to(tags: String): TagSet {
        return tags.split(".").toSet()
    }

    @TypeConverter
    fun from(tags: TagSet): String {
        return tags.joinToString(".")
    }
}
