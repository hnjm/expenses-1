package com.unex.expenses.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.unex.expenses.models.Spending
import com.unex.expenses.persistence.converters.DateConverter
import com.unex.expenses.persistence.converters.TagsConverter

@Database(entities = [Spending::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, TagsConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun spendingDao(): SpendingDao
}
