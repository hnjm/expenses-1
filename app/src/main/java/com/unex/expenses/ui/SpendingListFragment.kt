package com.unex.expenses.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unex.expenses.R
import com.unex.expenses.SpendingList
import com.unex.expenses.adapters.SpendingListAdapter
import com.unex.expenses.dialogs.TagsDialog
import com.unex.expenses.events.TagsPicked
import com.unex.expenses.vms.SpendingListViewModel
import kotlinx.android.synthetic.main.content_spendings.view.*
import kotlinx.android.synthetic.main.fragment_spendings.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class SpendingListFragment : Fragment() {

    private val spendingListAdapter: SpendingListAdapter = SpendingListAdapter()
    private lateinit var model: SpendingListViewModel

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        model = ViewModelProviders.of(this).get(SpendingListViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_spendings, container, false)
        view.spendingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = spendingListAdapter
        }
        val tagsDialog = TagsDialog()
        view.applyFiltersButton.setOnClickListener {
            tagsDialog.show(fragmentManager, tagsDialog.javaClass.name)
        }
        view.addSpendingButton.setOnClickListener {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, NewSpendingFragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        activity?.setTitle(R.string.title_spendings)
        EventBus.getDefault().register(this)
        model.getSpendings().observe(this, Observer<SpendingList> { storedSpendings ->
            storedSpendings?.let { spendingListAdapter.setSpendings(it) }
        })
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onEvent(event: TagsPicked) {
        model.setSelectedTags(event.tags)
    }
}
