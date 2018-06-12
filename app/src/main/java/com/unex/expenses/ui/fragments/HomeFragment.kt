package com.unex.expenses.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import com.unex.expenses.R
import com.unex.expenses.ui.activities.SettingsActivity
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                startActivity(Intent(activity, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
        activity?.setTitle(R.string.title_home)
//        fragmentManager
//                ?.beginTransaction()
//                ?.add(R.id.monthTotals, SpentTabFragment())
//                ?.commit()
    }
}
