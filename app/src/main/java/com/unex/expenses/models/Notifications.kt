package com.unex.expenses.models

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import com.unex.expenses.ALARM_RECEIVER_CODE
import com.unex.expenses.DEFAULT_REMINDER_TIME
import com.unex.expenses.KEY_REMINDER_TIME
import com.unex.expenses.receivers.AlarmReceiver
import java.util.*

object Notifications {

    fun scheduleAlarm(context: Context) {
        val (manager, pendingIntent) = getAlarmContext(context)
        manager.setInexactRepeating(
                AlarmManager.RTC,
                getAlarmTime(context),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        )
    }

    fun cancelAlarm(context: Context) {
        val (manager, pendingIntent) = getAlarmContext(context)
        manager.cancel(pendingIntent)
    }

    private fun getAlarmContext(context: Context): Pair<AlarmManager, PendingIntent> {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
                context,
                ALARM_RECEIVER_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        return Pair(manager, pendingIntent)
    }

    private fun getAlarmTime(context: Context): Long {
        val (hour, minute) = getReminderTime(context)
        val calendar = Calendar.getInstance()
        return with(calendar) {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (before(Calendar.getInstance())) add(Calendar.DAY_OF_MONTH, 1)
            timeInMillis
        }
    }

    private fun getReminderTime(context: Context): Pair<Int, Int> {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val stringTime = preferences.getString(KEY_REMINDER_TIME, DEFAULT_REMINDER_TIME)
        val hour = stringTime.split(":")[0].toInt()
        val minute = stringTime.split(":")[1].toInt()
        return Pair(hour, minute)
    }
}
