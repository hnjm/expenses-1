package com.unex.expenses.models

import com.unex.expenses.SpendingList
import com.unex.expenses.TagSet

data class SpendingsQuery(val spendings: SpendingList, val tags: TagSet)
