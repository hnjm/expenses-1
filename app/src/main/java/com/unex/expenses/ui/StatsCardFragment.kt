package com.unex.expenses.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unex.expenses.LABEL
import com.unex.expenses.R
import com.unex.expenses.VALUE
import kotlinx.android.synthetic.main.fragment_stats_card.*

class StatsCardFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View = inflater.inflate(R.layout.fragment_stats_card, container, false)

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        arguments?.let {
            statLabel.text = it.getString(LABEL)
            statValue.text = getString(R.string.value_amount, it.getInt(VALUE))
        }
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
