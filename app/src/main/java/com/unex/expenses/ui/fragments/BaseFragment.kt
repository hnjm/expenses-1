package com.unex.expenses.ui.fragments

import android.support.v4.app.Fragment
import com.unex.expenses.R

open class BaseFragment : Fragment() {

    fun navigateTo(fragment: Fragment) {
        fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(
                        R.animator.fade_in,
                        R.animator.fade_out,
                        R.animator.fade_in,
                        R.animator.fade_out
                )
                ?.replace(R.id.container, fragment)
                ?.addToBackStack(null)
                ?.commit()
    }
}
