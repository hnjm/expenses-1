package com.unex.expenses.models

import com.unex.expenses.SpendingList
import com.unex.expenses.TagSet

data class SpendingQuery(val spendings: SpendingList, val tags: TagSet)
