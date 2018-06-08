package com.unex.expenses.ui.fragments

import android.app.DatePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unex.expenses.DIALOG_TAGS
import com.unex.expenses.R
import com.unex.expenses.TagSet
import com.unex.expenses.TagsPicked
import com.unex.expenses.dialogs.TagsDialog
import com.unex.expenses.models.DateHelper
import com.unex.expenses.models.Spending
import com.unex.expenses.models.Validations
import com.unex.expenses.vms.NewSpendingViewModel
import kotlinx.android.synthetic.main.content_new_spending.*
import kotlinx.android.synthetic.main.fragment_new_spending.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*

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
    ): View = inflater.inflate(R.layout.fragment_new_spending, container, false)

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        dateButton.text = DateHelper.getDateString(model.getDate())
        dateButton.setOnClickListener {
            val listener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                model.setDate(year, month, day)
            }
            DatePickerDialog(
                    activity,
                    listener,
                    DateHelper.getCurrentYear(),
                    DateHelper.getCurrentMonth(),
                    DateHelper.getCurrentDay()
            ).show()
        }

        model.dateObs.observe(this, Observer<Date> { date ->
            date?.let { dateButton.text = DateHelper.getDateString(it) }
        })

        model.tagsObs.observe(this, Observer<TagSet> { tags ->
            tags?.let { if (it.isNotEmpty()) tagsButton.text = it.joinToString(", ") }
        })

        val tagsDialog = TagsDialog()
        tagsButton.setOnClickListener {
            tagsDialog.show(fragmentManager, DIALOG_TAGS)
        }

        createSpendingButton.setOnClickListener {
            try {
                val amount = Validations.validateAmount(amountInput.text.toString()).toInt()
                val description = descriptionInput.text.toString().let {
                    if (it.isEmpty()) null else it
                }
                val spending = Spending(amount, model.getDate(), model.getTags(), description)
                AsyncTask.execute { model.addSpending(spending) }
                fragmentManager?.popBackStack()
            } catch (exc: NumberFormatException) {
                Snackbar.make(view, R.string.error_invalid_amount, Snackbar.LENGTH_SHORT).show();
            } catch (exc: Exception) {
                Snackbar.make(view, R.string.error_empty_amount, Snackbar.LENGTH_SHORT).show();
            }
        }
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
        model.setTags(event.tags)
    }
}
