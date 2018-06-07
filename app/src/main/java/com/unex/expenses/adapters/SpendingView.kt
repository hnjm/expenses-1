package com.unex.expenses.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.unex.expenses.R
import com.unex.expenses.TriggerDeleteSpending
import com.unex.expenses.models.DateHelper
import com.unex.expenses.models.Spending
import kotlinx.android.synthetic.main.item_spending.view.*
import org.greenrobot.eventbus.EventBus

class SpendingView(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(spending: Spending) {
        view.amount.text = view.context.getString(R.string.value_amount, spending.getAmount())
        view.date.text = DateHelper.getDateString(spending.getDate())
//        view.tags.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
//        view.tags.adapter = TagAdapter(spending.getTags().toList())
        spending.getDescription()?.let {
            view.description.text = it
            view.description.visibility = View.VISIBLE
        }
        view.deleteButton.setOnClickListener {
            spending.getId()?.let {
                EventBus.getDefault().post(TriggerDeleteSpending(it))
            }
        }
    }
}
