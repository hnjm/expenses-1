package com.unex.expenses.dialogs

import android.content.Context
import android.content.res.TypedArray
import android.preference.DialogPreference
import android.util.AttributeSet
import android.view.View
import android.widget.TimePicker
import com.unex.expenses.DEFAULT_TIME
import com.unex.expenses.R

class TimePreference(context: Context, attrs: AttributeSet) : DialogPreference(context, attrs) {

    private var lastHour = 0
    private var lastMinute = 0
    private lateinit var picker: TimePicker

    init {
        positiveButtonText = context.getString(R.string.dialog_action_set)
        negativeButtonText = context.getString(R.string.dialog_action_cancel)
    }

    override fun onCreateDialogView(): View {
        picker = TimePicker(context)
        picker.setIs24HourView(true)
        picker.hour = lastHour
        picker.minute = lastMinute
        return picker as TimePicker
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        super.onDialogClosed(positiveResult)
        if (positiveResult) {
            lastHour = picker.hour
            lastMinute = picker.minute
            val time = "$lastHour:$lastMinute"
            if (callChangeListener(time)) {
                persistString(time)
            }
        }
    }

    override fun onGetDefaultValue(array: TypedArray, index: Int): Any? {
        return array.getString(index)
    }

    override fun onSetInitialValue(restoreValue: Boolean, defaultValue: Any?) {
        val time = if (restoreValue) {
            getPersistedString(defaultValue?.toString() ?: DEFAULT_TIME)
        } else {
            defaultValue!!.toString()
        }
        lastHour = time.split(":")[0].toInt()
        lastMinute = time.split(":")[1].toInt()
    }
}
