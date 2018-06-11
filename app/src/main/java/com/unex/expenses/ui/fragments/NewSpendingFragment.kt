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
import com.unex.expenses.models.Dates
import com.unex.expenses.models.Validations
import com.unex.expenses.persistence.entities.Spending
import com.unex.expenses.vms.NewSpendingViewModel
import kotlinx.android.synthetic.main.content_new_spending.*
import kotlinx.android.synthetic.main.fragment_new_spending.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.fragment_home.*


@Suppress("NAME_SHADOWING")
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
        dateButton.text = Dates.dateString(model.getDate())
        dateButton.setOnClickListener {
            val listener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                model.setDate(year, month, day)
            }
            DatePickerDialog(
                    activity,
                    listener,
                    Dates.currentYear(),
                    Dates.currentMonth(),
                    Dates.currentDay()
            ).show()
        }

        model.dateObs.observe(this, Observer<Date> { date ->
            date?.let { dateButton.text = Dates.dateString(it) }
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
                val amount = Validations.parseAmount(amountInput.text.toString())
                val description = descriptionInput.text.toString().let {
                    if (it.isEmpty()) null else it
                }
                val spending = Spending(amount, model.getDate(), description, model.getTags())
                AsyncTask.execute { model.addSpending(spending) }

                val inputMethodManager = activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                val view = activity!!.findViewById<View>(R.id.contentLayout) ?: View(activity)
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

                fragmentManager?.popBackStack()

            } catch (exc: NumberFormatException) {
                Snackbar.make(view, R.string.error_invalid_amount, Snackbar.LENGTH_SHORT).show();
            } catch (exc: Validations.EmptyAmountException) {
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
