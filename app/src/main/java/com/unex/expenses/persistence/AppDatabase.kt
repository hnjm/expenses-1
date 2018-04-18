package com.unex.expenses.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.unex.expenses.models.SpendingEntity

@Database(entities = [SpendingEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun spendingDao(): SpendingDao
}