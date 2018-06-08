package com.unex.expenses.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unex.expenses.R
import com.unex.expenses.SpendingList
import com.unex.expenses.vms.HomeViewModel
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private lateinit var model: HomeViewModel

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        model = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        addSpendingButton.setOnClickListener { navigateTo(NewSpendingFragment()) }
        spendingListButton.setOnClickListener { navigateTo(SpendingListFragment()) }
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
