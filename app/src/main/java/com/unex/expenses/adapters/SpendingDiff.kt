package com.unex.expenses.adapters

import android.support.v7.util.DiffUtil
import com.unex.expenses.models.Spending

class SpendingDiff : DiffUtil.ItemCallback<Spending>() {

    override fun areItemsTheSame(oldItem: Spending?, newItem: Spending?): Boolean {
        return oldItem?.getId() === newItem?.getId()
    }

    override fun areContentsTheSame(oldItem: Spending?, newItem: Spending?): Boolean {
        return oldItem === newItem
    }
}
