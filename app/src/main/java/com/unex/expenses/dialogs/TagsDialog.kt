package com.unex.expenses.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.unex.expenses.R
import com.unex.expenses.TagsPicked
import org.greenrobot.eventbus.EventBus

class TagsDialog : DialogFragment() {

    private val selections: MutableSet<Int> = mutableSetOf()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val tags = resources.getStringArray(R.array.default_tags)
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.dialog_title_tags)
        builder.setMultiChoiceItems(tags, getSelectedTags(tags), { _, which, isChecked ->
            if (isChecked) selections.add(which) else selections.remove(which)
        })
        builder.setPositiveButton(R.string.dialog_action_ok, { _, _ ->
            val tagList = selections.map { tags[it] }
            EventBus.getDefault().post(TagsPicked(tagList.toSet()))
        })
        return builder.create();
    }

    private fun getSelectedTags(tags: Array<String>): BooleanArray {
        val emptyOptions = tags.map { false }.toBooleanArray()
        return selections.fold(emptyOptions) { acc, i ->
            acc[i] = true
            acc
        }
    }
}
