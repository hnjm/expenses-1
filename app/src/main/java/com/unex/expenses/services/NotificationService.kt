package com.unex.expenses.services

import android.app.IntentService
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.preference.PreferenceManager
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import com.unex.expenses.R
import com.unex.expenses.ui.MainActivity

class NotificationService : IntentService("NotificationService") {

    override fun onHandleIntent(p0: Intent?) {
        sendNotification("remember_load_data")
    }

    private fun sendNotification(notificationType: String) {

        val notification = buildNotification(getNotificationTitle(notificationType),
                                             getNotificationText(notificationType))

        val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val showNotification = preferences.getBoolean("notification_remember", true)

        if (showNotification) {
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(0, notification)
        }
    }

    private fun buildNotification(title: String, text: String): Notification {
        val contentIntent = buildNotificationIntent()

        val notification = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(resources.getColor(R.color.primary))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(longArrayOf(200, 300, 200, 300))
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .build()

        return notification
    }

    private fun buildNotificationIntent(): PendingIntent {
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addParentStack(MainActivity::class.java!!)
        stackBuilder.addNextIntent(Intent(this, MainActivity::class.java))
        val contentIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        return contentIntent!!
    }

    private fun getNotificationTitle(notificationType: String): String {
        return if (notificationType.equals("remember_load_data", ignoreCase = true))
                resources.getString(R.string.notification_remember_load_data_title)
               else
                resources.getString(R.string.notification_default_title)
    }

    private fun getNotificationText(notificationType: String): String {
        return if (notificationType.equals("remember_load_data", ignoreCase = true))
            resources.getString(R.string.notification_remember_load_data_text)
        else
            resources.getString(R.string.notification_default_text)
    }


}