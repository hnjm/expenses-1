package com.unex.expenses.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.unex.expenses.*
import com.unex.expenses.adapters.SpendingAdapter
import com.unex.expenses.dialogs.DeleteConfirmationDialog
import com.unex.expenses.dialogs.TagsDialog
import com.unex.expenses.vms.SpendingListViewModel
import kotlinx.android.synthetic.main.content_spendings.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class SpendingListFragment : Fragment() {

    private val spendingListAdapter = SpendingAdapter()
    private lateinit var model: SpendingListViewModel

    private val tagsDialog = TagsDialog()

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setHasOptionsMenu(true)
        model = ViewModelProviders.of(this).get(SpendingListViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter -> {
                tagsDialog.show(fragmentManager, DIALOG_TAGS)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View = inflater.inflate(R.layout.fragment_spendings, container, false)

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)
        spendingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = spendingListAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.setTitle(R.string.title_spendings)
        EventBus.getDefault().register(this)
        model.spendingsObs.observe(this, Observer<SpendingList> { storedSpendings ->
            storedSpendings?.let { spendingListAdapter.submitList(it) }
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

    @Subscribe
    fun onEvent(event: TriggerDeleteSpending) {
        val dialog = DeleteConfirmationDialog.create(event.spendingId)
        dialog.show(fragmentManager, DIALOG_DELETE_CONFIRMATION)
    }

    @Subscribe
    fun onEvent(event: DeleteSpending) {
        model.deleteSpending(event.spendingId)
    }
}
