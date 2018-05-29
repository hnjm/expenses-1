package com.unex.expenses.adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.unex.expenses.models.DateHelper
import com.unex.expenses.models.Spending
import kotlinx.android.synthetic.main.item_spending.view.*

class SpendingView(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(spending: Spending) {
        view.amount.text = spending.getAmount().toString()
        view.date.text = DateHelper.getDateString(spending.getDate())
        view.tags.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        view.tags.adapter = TagAdapter(spending.getTags().toList())
        spending.getDescription()?.let {
            view.description.text = it
            view.description.visibility = View.VISIBLE
        }
    }
}
