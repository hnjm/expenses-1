package com.unex.expenses.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.unex.expenses.R

class TagAdapter(val tags: List<String>) : RecyclerView.Adapter<TagView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagView {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_tag, parent, false)
        return TagView(view)
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    override fun onBindViewHolder(holder: TagView, position: Int) {
        holder.bind(tags[position])
    }
}
