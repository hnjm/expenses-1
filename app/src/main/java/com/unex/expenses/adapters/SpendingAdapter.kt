package com.unex.expenses.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.unex.expenses.R
import com.unex.expenses.persistence.entities.Spending

class SpendingAdapter : ListAdapter<Spending, SpendingView>(SpendingDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendingView {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_spending, parent, false)
        return SpendingView(view)
    }

    override fun onBindViewHolder(holder: SpendingView, position: Int) {
        holder.bind(getItem(position))
    }
}
