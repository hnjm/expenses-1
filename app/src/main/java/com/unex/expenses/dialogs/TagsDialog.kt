package com.unex.expenses.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.unex.expenses.R
import com.unex.expenses.events.TagsPicked
import org.greenrobot.eventbus.EventBus

class TagsDialog : DialogFragment() {

    private val selections: MutableSet<Int> = mutableSetOf()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val tags = resources.getStringArray(R.array.default_tags)
        val selectionFlags = selections.fold(
                tags.map { false }.toBooleanArray()
        ) { acc, i ->
            acc[i] = true
            acc
        }
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.dialog_tags_title)
        builder.setMultiChoiceItems(R.array.default_tags, selectionFlags, { _, which, isChecked ->
            if (isChecked) {
                selections.add(which)
            } else {
                selections.remove(which)
            }
        })
        builder.setPositiveButton(R.string.dialog_tags_ok, { _, _ ->

            val tagList = selections.map { tags[it] }
            EventBus.getDefault().post(TagsPicked(tagList.toSet()))
        })
        return builder.create();
    }
}
