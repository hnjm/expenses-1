package com.unex.expenses.dialogs

import android.content.Context
import android.content.res.TypedArray
import android.widget.TimePicker
import android.preference.DialogPreference
import android.util.AttributeSet
import android.view.View


class TimePreference(ctxt: Context, attrs: AttributeSet) : DialogPreference(ctxt, attrs) {
    private var lastHour = 0
    private var lastMinute = 0
    private var picker: TimePicker? = null

    init {

        positiveButtonText = "Set"
        negativeButtonText = "Cancel"
    }

    override fun onCreateDialogView(): View {
        picker = TimePicker(context)
        picker!!.setIs24HourView(true)
        return picker as TimePicker
    }

    protected override fun onBindDialogView(v: View) {
        super.onBindDialogView(v)

        picker!!.currentHour = lastHour
        picker!!.currentMinute = lastMinute
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        super.onDialogClosed(positiveResult)

        if (positiveResult) {
            lastHour = picker!!.currentHour
            lastMinute = picker!!.currentMinute

            val time = lastHour.toString() + ":" + lastMinute.toString()

            if (callChangeListener(time)) {
                persistString(time)
            }
        }
    }

    override fun onGetDefaultValue(a: TypedArray, index: Int): Any? {
        return a.getString(index)
    }

    override fun onSetInitialValue(restoreValue: Boolean, defaultValue: Any?) {
        var time: String? = null

        if (restoreValue) {
            if (defaultValue == null) {
                time = getPersistedString("00:00")
            } else {
                time = getPersistedString(defaultValue.toString())
            }
        } else {
            time = defaultValue!!.toString()
        }

        lastHour = getHour(time)
        lastMinute = getMinute(time)
    }

    companion object {

        fun getHour(time: String?): Int {
            val pieces = time!!.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            return Integer.parseInt(pieces[0])
        }

        fun getMinute(time: String?): Int {
            val pieces = time!!.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            return Integer.parseInt(pieces[1])
        }
    }
}