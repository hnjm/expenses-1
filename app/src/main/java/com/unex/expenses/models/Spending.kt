package com.unex.expenses.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "spendings")
class Spending(
        private val amount: Int,
        private val date: Date,
        private val tags: Set<String>,
        private val description: String?
) {
    @PrimaryKey(autoGenerate = true)
    private var id: Long? = null

    fun setId(id: Long) {
        this.id = id
    }

    fun getId(): Long? = this.id
    fun getAmount() = this.amount
    fun getDate() = this.date
    fun getTags() = this.tags
    fun getDescription() = this.description
}
