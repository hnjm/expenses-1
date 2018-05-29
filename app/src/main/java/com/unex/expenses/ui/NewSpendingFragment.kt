package com.unex.expenses.ui

import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unex.expenses.R
import com.unex.expenses.dialogs.TagsDialog
import com.unex.expenses.events.TagsPicked
import com.unex.expenses.models.DateHelper
import com.unex.expenses.models.SpendingHelper
import com.unex.expenses.vms.NewSpendingViewModel
import kotlinx.android.synthetic.main.content_new_spending.*
import kotlinx.android.synthetic.main.content_new_spending.view.*
import kotlinx.android.synthetic.main.fragment_new_spending.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

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
        val tagsDialog = TagsDialog()
        val view = inflater.inflate(R.layout.fragment_new_spending, container, false)
        view.dateButton.text = DateHelper.getDateString(model.date)
        view.tagsButton.setOnClickListener {
            tagsDialog.show(fragmentManager, tagsDialog.javaClass.name)
        }
        view.dateButton.setOnClickListener {
            DatePickerDialog(
                    activity,
                    DatePickerDialog.OnDateSetListener { _, year, month, day ->
                        model.date = DateHelper.createDate(year, month, day)
                        view.dateButton.text = DateHelper.getDateString(model.date)
                    },
                    DateHelper.getCurrentYear(),
                    DateHelper.getCurrentMonth(),
                    DateHelper.getCurrentDay()
            ).show()
        }
        view.createSpendingButton.setOnClickListener {
            SpendingHelper.createSpending(
                    view.amountInput.text.toString(),
                    model.date,
                    view.tagsButton.text.toString(),
                    view.descriptionInput.text.toString()
            ).let {
                AsyncTask.execute { model.addSpending(it) }
                fragmentManager?.popBackStack()
            }
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
        activity?.setTitle(R.string.title_new_spending)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onEvent(event: TagsPicked) {
        tagsButton.text = event.tags.joinToString(", ")
    }
}
