package com.unex.expenses.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.unex.expenses.services.NotificationService

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.startService(Intent(context, NotificationService::class.java))
    }
}
