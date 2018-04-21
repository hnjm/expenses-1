package com.unex.expenses.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unex.expenses.R
import com.unex.expenses.models.Helper
import com.unex.expenses.vms.NewSpendingViewModel
import kotlinx.android.synthetic.main.content_new_spending.view.*
import kotlinx.android.synthetic.main.fragment_new_spending.view.*

class NewSpendingFragment : Fragment() {

    private lateinit var model: NewSpendingViewModel

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        model = ViewModelProviders.of(this).get(NewSpendingViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_spending, container, false)
        view.createSpendingButton.setOnClickListener {
            Helper.createSpending(
                    view.amountInput.text.toString(),
                    view.dateInput.text.toString(),
                    view.tagsInput.text.toString(),
                    view.descriptionInput.text.toString()
            ).let {
                AsyncTask.execute { model.addSpending(it) }
                fragmentManager?.popBackStack()
            }
        }
        return view
    }
}
