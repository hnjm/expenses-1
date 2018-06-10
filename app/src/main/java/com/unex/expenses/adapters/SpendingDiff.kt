package com.unex.expenses.adapters

import android.support.v7.util.DiffUtil
import com.unex.expenses.persistence.entities.Spending

class SpendingDiff : DiffUtil.ItemCallback<Spending>() {

    override fun areItemsTheSame(oldItem: Spending?, newItem: Spending?): Boolean {
        return oldItem?.id === newItem?.id
    }

    override fun areContentsTheSame(oldItem: Spending?, newItem: Spending?): Boolean {
        return oldItem === newItem
    }
}
