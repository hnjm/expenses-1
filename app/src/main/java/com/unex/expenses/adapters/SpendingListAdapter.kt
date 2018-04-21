package com.unex.expenses.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unex.expenses.R
import com.unex.expenses.SpendingList
import com.unex.expenses.models.Helper
import com.unex.expenses.models.Spending
import kotlinx.android.synthetic.main.item_spending.view.*

class SpendingListAdapter : RecyclerView.Adapter<SpendingListAdapter.ViewHolder>() {

    private var spendings: SpendingList = listOf()

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(spending: Spending) {
            view.amount.text = spending.getAmount().toString()
            view.date.text = Helper.getDateString(spending.getDate())
            view.tags.text = spending.getTags().joinToString(" - ")
            spending.getDescription()?.let {
                view.description.text = it
                view.description.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): SpendingListAdapter.ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_spending, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(spendings[position])
    }

    override fun getItemCount() = spendings.size

    fun setSpendings(newSpendings: SpendingList) {
        spendings = newSpendings
        notifyDataSetChanged()
    }
}