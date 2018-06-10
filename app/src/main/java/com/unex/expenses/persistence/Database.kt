package com.unex.expenses.persistence

import android.arch.persistence.room.Room
import android.content.Context
import com.unex.expenses.DB_NAME

object Database {

    private lateinit var database: AppDatabase

    fun setUp(context: Context) {
        database = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    fun get() = database
}
