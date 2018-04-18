package com.unex.expenses.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "spendings")
class SpendingEntity(
        @PrimaryKey(autoGenerate = true) private val id: Long,
        private val amount: Double,
        private val date: Date,
        private val tags: List<String>,
        private val description: String?
) {
    fun getId(): Long = this.id
    fun getAmount() = this.amount
    fun getDate() = this.date
    fun getTags() = this.tags
    fun getDescription() = this.description
}