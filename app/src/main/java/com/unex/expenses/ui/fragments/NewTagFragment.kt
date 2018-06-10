package com.unex.expenses.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unex.expenses.R

class NewTagFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View = inflater.inflate(R.layout.fragment_new_tag, container, false)
}
