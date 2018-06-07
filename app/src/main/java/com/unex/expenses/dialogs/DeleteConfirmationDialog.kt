package com.unex.expenses.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.unex.expenses.DeleteSpending
import com.unex.expenses.R
import com.unex.expenses.SPENDING_ID
import org.greenrobot.eventbus.EventBus

class DeleteConfirmationDialog : DialogFragment() {

    override fun onCreateDialog(state: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.dialog_message_delete_confirmation)
        builder.setPositiveButton(R.string.dialog_action_delete, { _, _ ->
            arguments?.let {
                val spendingId = arguments?.getLong(SPENDING_ID)
                spendingId?.let { EventBus.getDefault().post(DeleteSpending(it)) }
            }
        })
        builder.setNegativeButton(R.string.dialog_action_cancel, { dialog, _ ->
            dialog.cancel()
        })
        return builder.create()
    }

    companion object {
        fun create(spendingId: Long): DeleteConfirmationDialog {
            val fragment = DeleteConfirmationDialog()
            fragment.arguments = Bundle().apply {
                putLong(SPENDING_ID, spendingId)
            }
            return fragment
        }
    }
}
