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
import kotlinx.android.synthetic.main.content_daily_spending.view.*
import kotlinx.android.synthetic.main.fragment_daily_spending.view.*

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
        view.spendingsButton.setOnClickListener {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, SpendingListFragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }
        view.addSpendingButton.setOnClickListener {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, NewSpendingFragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        dailyLimitLabel.text = model.getDailyLimit().toString()
        model.daySpendings.observe(this, Observer<SpendingList> {
            val count = it?.fold(0, { acum, spending -> acum + spending.getAmount() }) ?: 0
            dailySpentLabel.text = count.toString()
            val available = model.getDailyLimit() - count
            dailyAvailableLabel.text = if (available > 0) available.toString() else "0"
        })
    }
}
