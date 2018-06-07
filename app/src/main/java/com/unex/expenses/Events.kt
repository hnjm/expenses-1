package com.unex.expenses

data class TagsPicked(val tags: TagSet)
data class TriggerDeleteSpending(val spendingId: Long)
data class DeleteSpending(val spendingId: Long)
