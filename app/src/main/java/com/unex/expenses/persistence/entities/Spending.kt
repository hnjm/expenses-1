package com.unex.expenses.persistence.entities

import android.arch.persistence.room.Entity
import com.unex.expenses.TagSet
import java.util.*

@Entity(tableName = "spendings")
class Spending(
        val amount: Int,
        val date: Date,
        val description: String?,
        val tags: TagSet
) : BaseEntity()
