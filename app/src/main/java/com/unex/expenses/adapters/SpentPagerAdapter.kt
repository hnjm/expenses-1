package com.unex.expenses.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.unex.expenses.models.Moment
import com.unex.expenses.ui.fragments.SpentTabFragment

class SpentPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        return SpentTabFragment.create(when (position) {
            1 -> Moment.MONTH
            else -> Moment.DAY
        })
    }

    override fun getCount(): Int {
        return 2
    }
}
