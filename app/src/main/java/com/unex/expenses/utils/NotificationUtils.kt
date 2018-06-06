package com.unex.expenses.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import com.unex.expenses.receivers.AlarmReceiver
import java.sql.Time
import java.util.*

class NotificationUtils {

    companion object {

        fun scheduleAlarm(context: Context) {
            cancelAlarms(context)

            val pendingIntent = getPendingAlarmReceiverIntent(context, PendingIntent.FLAG_UPDATE_CURRENT)
            val timeInMillis = getTimeInMillis(context, "notification_hour")

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, timeInMillis,
                    AlarmManager.INTERVAL_DAY, pendingIntent)
        }

        fun cancelAlarms(context: Context) {
            val pendingIntent = getPendingAlarmReceiverIntent(context, PendingIntent.FLAG_CANCEL_CURRENT)

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
        }

        private fun getPendingAlarmReceiverIntent(context: Context, flag: Int): PendingIntent {
            val intent = Intent(context, AlarmReceiver::class.java)
            val pIntent = PendingIntent.getBroadcast(context, AlarmReceiver.REQUEST_CODE,
                    intent, flag)

            return pIntent
        }

        private fun getTimeInMillis(context: Context, key: String): Long {
            val notificationTime = getNotificationTime(context, key)
            val cal = Calendar.getInstance()
            cal.set(Calendar.HOUR_OF_DAY, notificationTime.hours)
            cal.set(Calendar.MINUTE, notificationTime.minutes)
            cal.set(Calendar.SECOND, notificationTime.seconds)
            return cal.getTimeInMillis()
        }

        private fun getNotificationTime(context: Context, key: String): Time {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val stringTime = preferences.getString(key, "00:00")
            val hours = stringTime.split(":")[0].toInt()
            val minutes = stringTime.split(":")[1].toInt()
            val seconds = 0
            return Time(hours, minutes, seconds)
        }
    }
}