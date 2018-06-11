package com.unex.expenses.services

import android.app.IntentService
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.preference.PreferenceManager
import android.support.v4.app.NotificationCompat
import com.unex.expenses.KEY_REMINDER_ENABLED
import com.unex.expenses.NOTIFICATION_CHANNEL
import com.unex.expenses.R
import com.unex.expenses.models.NotificationType
import com.unex.expenses.ui.activities.MainActivity

class NotificationService : IntentService("NotificationService") {

    override fun onHandleIntent(intent: Intent?) {
        sendReminderNotification(NotificationType.REMINDER)
    }

    private fun sendReminderNotification(notificationType: NotificationType) {
        val notification = createNotification(
                resources.getString(getTitle(notificationType)),
                resources.getString(getText(notificationType))
        )
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val notificationsEnabled = preferences.getBoolean(KEY_REMINDER_ENABLED, true)
        if (notificationsEnabled) {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(1, notification)
        }
    }

    private fun createNotification(title: String, text: String): Notification {
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.ic_money)
                .setColorized(true)
                .setColor(getColor(R.color.primary))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(longArrayOf(200, 300, 200, 300))
                .setStyle(NotificationCompat.BigTextStyle().bigText(text))
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(getPendingIntent())
                .setAutoCancel(true)
                .build()
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        return PendingIntent.getActivity(this, 0, intent, 0)
    }

    private fun getTitle(notificationType: NotificationType): Int {
        return when (notificationType) {
            NotificationType.REMINDER -> R.string.notification_reminder_title
        }
    }

    private fun getText(notificationType: NotificationType): Int {
        return when (notificationType) {
            NotificationType.REMINDER -> R.string.notification_reminder_text
        }
    }
}
