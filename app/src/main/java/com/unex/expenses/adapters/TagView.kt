package com.unex.expenses.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_tag.view.*

class TagView(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(tag: String) {
        view.tagItem.text = tag
    }
}
