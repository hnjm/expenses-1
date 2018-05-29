package com.unex.expenses.models

import java.util.*

object SpendingHelper {

    fun createSpending(amount: String, date: Date, tags: String, description: String) =
            Spending(
                    amount = amount.toInt(),
                    date = date,
                    tags = tags.split(", ").toSet(),
                    description = if (description.isEmpty()) null else description
            )
}
