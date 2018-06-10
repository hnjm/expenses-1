package com.unex.expenses.models

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import com.unex.expenses.ALARM_RECEIVER_CODE
import com.unex.expenses.DEFAULT_TIME
import com.unex.expenses.KEY_REMINDER_TIME
import com.unex.expenses.receivers.AlarmReceiver
import java.util.*

object Notifications {

    fun scheduleNewAlarm(context: Context) {
        cancelRunningAlarm(context)
        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.setInexactRepeating(
                AlarmManager.RTC,
                getScheduledTime(context),
                AlarmManager.INTERVAL_DAY,
                getPendingIntent(context, PendingIntent.FLAG_UPDATE_CURRENT)
        )
    }

    fun cancelRunningAlarm(context: Context) {
        val pendingIntent = getPendingIntent(context, PendingIntent.FLAG_CANCEL_CURRENT)
        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.cancel(pendingIntent)
    }

    private fun getPendingIntent(context: Context, flag: Int): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java)
        return PendingIntent.getBroadcast(context, ALARM_RECEIVER_CODE, intent, flag)
    }

    private fun getScheduledTime(context: Context): Long {
        val (hour, minute) = getNotificationTime(context)
        val calendar = Calendar.getInstance()
        return with(calendar) {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            timeInMillis
        }
    }

    private fun getNotificationTime(context: Context): Pair<Int, Int> {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val stringTime = preferences.getString(KEY_REMINDER_TIME, DEFAULT_TIME)
        val hour = stringTime.split(":")[0].toInt()
        val minute = stringTime.split(":")[1].toInt()
        return Pair(hour, minute)
    }
}
