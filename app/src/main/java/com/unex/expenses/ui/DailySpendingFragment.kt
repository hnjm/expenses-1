package com.unex.expenses.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unex.expenses.R
import com.unex.expenses.SpendingList
import com.unex.expenses.vms.DailySpendingViewModel
import kotlinx.android.synthetic.main.content_daily_spending.*
import kotlinx.android.synthetic.main.fragment_daily_spending.*

class DailySpendingFragment : Fragment() {

    private lateinit var model: DailySpendingViewModel

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        model = ViewModelProviders.of(this).get(DailySpendingViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daily_spending, container, false)
        val limit = model.getDailyLimit()

        model.daySpendings.observe(this, Observer<SpendingList> {
            val spent = it?.fold(0, { acum, spending -> acum + spending.getAmount() }) ?: 0
            val available = if (limit - spent > 0) limit - spent else 0
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.spent, StatsCardFragment.create("Spent", spent))
                    ?.replace(R.id.available, StatsCardFragment.create("Available", available))
                    ?.commit()
        })

        fragmentManager
                ?.beginTransaction()
                ?.add(R.id.limit, StatsCardFragment.create("Limit", limit))
                ?.commit()

        return view
    }

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        addSpendingButton.setOnClickListener {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, NewSpendingFragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }
        spendingListButton.setOnClickListener {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, SpendingListFragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }
}
