package com.unex.expenses.persistence

import android.arch.persistence.room.Room
import android.content.Context

object Database {

    private lateinit var database: AppDatabase

    fun setUp(context: Context) {
        database = Room.databaseBuilder(context, AppDatabase::class.java, "spendings").build()
    }

    fun get() = database
}
