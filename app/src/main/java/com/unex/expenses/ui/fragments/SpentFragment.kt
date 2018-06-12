package com.unex.expenses.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unex.expenses.R
import com.unex.expenses.adapters.SpentPagerAdapter
import kotlinx.android.synthetic.main.fragment_spent.*

class SpentFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View = inflater.inflate(R.layout.fragment_spent, container, false)

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        pager.adapter = SpentPagerAdapter(childFragmentManager)
        dots.setupWithViewPager(pager)
    }
}
