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
    ): View = inflater.inflate(R.layout.fragment_daily_spending, container, false)

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

    override fun onStart() {
        super.onStart()
        val limit = model.getDailyLimit()
        val limitLabel = getString(R.string.label_limit)
        val spentLabel = getString(R.string.label_spent)
        val availableLabel = getString(R.string.label_available)

        val limitFragment = StatsCardFragment.create(limitLabel, limit)
        fragmentManager
                ?.beginTransaction()
                ?.add(R.id.limit, limitFragment)
                ?.commit()

        model.spendingsObs.observe(this, Observer<SpendingList> {
            val spent = it?.fold(0, { acum, spending -> acum + spending.getAmount() }) ?: 0
            val available = if (limit - spent > 0) limit - spent else 0
            val spentFragment = StatsCardFragment.create(spentLabel, spent)
            val availableFragment = StatsCardFragment.create(availableLabel, available)
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.spent, spentFragment)
                    ?.replace(R.id.available, availableFragment)
                    ?.commit()
        })
    }
}
