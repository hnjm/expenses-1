package com.unex.expenses.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.unex.expenses.R
import com.unex.expenses.TriggerDeleteSpending
import com.unex.expenses.models.Dates
import com.unex.expenses.persistence.entities.Spending
import kotlinx.android.synthetic.main.item_spending.view.*
import org.greenrobot.eventbus.EventBus

class SpendingView(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(spending: Spending) {
        view.amount.text = view.context.getString(R.string.value_amount, spending.amount)
        view.date.text = Dates.dateString(spending.date)
        spending.description?.let {
            view.description.text = it
            view.description.visibility = View.VISIBLE
        }
        view.deleteButton.setOnClickListener {
            spending.id?.let {
                EventBus.getDefault().post(TriggerDeleteSpending(it))
            }
        }
    }
}
