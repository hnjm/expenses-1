package com.unex.expenses.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unex.expenses.R
import com.unex.expenses.SpendingList
import com.unex.expenses.models.Moment
import com.unex.expenses.vms.SpentTabViewModel
import kotlinx.android.synthetic.main.fragment_spent_tab.*
import java.text.NumberFormat

class SpentTabFragment : Fragment() {

    private lateinit var model: SpentTabViewModel

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        model = ViewModelProviders.of(this).get(SpentTabViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View = inflater.inflate(R.layout.fragment_spent_tab, container, false)

    override fun onStart() {
        super.onStart()
        arguments?.let {
            when (it.getString(MOMENT)) {
                Moment.DAY.name -> {
                    model.daySpendingsObs.observe(this, Observer<SpendingList> {
                        val limit = model.getDailyLimit()
                        val spent = it?.fold(0, { acum, spending ->
                            acum + spending.amount
                        }) ?: 0
                        val available = if (limit - spent > 0) limit - spent else 0
                        spentLabel.text = NumberFormat.getInstance().format(spent)
                        availableLabel.text = NumberFormat.getInstance().format(available)
                    })
                    tabLabel.text = getString(R.string.label_spent_day)
                }
                Moment.MONTH.name -> {
                    model.monthSpendingsObs.observe(this, Observer<SpendingList> {
                        val limit = model.getMonthlyLimit()
                        val spent = it?.fold(0, { acum, spending ->
                            acum + spending.amount
                        }) ?: 0
                        val available = if (limit - spent > 0) limit - spent else 0
                        spentLabel.text = NumberFormat.getInstance().format(spent)
                        availableLabel.text = NumberFormat.getInstance().format(available)
                    })
                    tabLabel.text = getString(R.string.label_spent_month)
                }
            }
        }
    }

    companion object {
        private const val MOMENT = "moment"

        fun create(moment: Moment): SpentTabFragment {
            val fragment = SpentTabFragment()
            fragment.arguments = Bundle().apply {
                putString(MOMENT, moment.name)
            }
            return fragment
        }
    }
}
