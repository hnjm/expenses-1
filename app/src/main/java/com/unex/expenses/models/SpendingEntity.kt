package com.unex.expenses.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class SpendingEntity(
        @PrimaryKey(autoGenerate = true) private val id: Int,
        private val amount: Double,
        private val tags: List<String>,
        private val description: String
) : Spending {
    // constructor() : this(null, null, null, null)

    fun getId(): Int = this.id

    override fun getAmount() = this.amount

    override fun getTags() = this.tags

    override fun getDescription() = this.description
}