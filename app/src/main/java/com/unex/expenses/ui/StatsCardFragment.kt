package com.unex.expenses.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.unex.expenses.LABEL
import com.unex.expenses.R
import com.unex.expenses.VALUE

class StatsCardFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats_card, container, false)
        arguments?.let {
            view.findViewById<TextView>(R.id.statLabel).text = it.getString(LABEL)
            view.findViewById<TextView>(R.id.statValue).text = "$ ${it.getInt(VALUE)}"
        }
        return view
    }

    companion object {
        fun create(label: String, value: Int): StatsCardFragment {
            val fragment = StatsCardFragment()
            fragment.arguments = Bundle().apply {
                putString(LABEL, label)
                putInt(VALUE, value)
            }
            return fragment
        }
    }
}
