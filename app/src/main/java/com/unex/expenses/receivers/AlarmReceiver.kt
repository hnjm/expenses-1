package com.unex.expenses.receivers

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import com.unex.expenses.services.NotificationService


class AlarmReceiver : BroadcastReceiver() {

    // Triggered by the Alarm periodically (starts the service to run task)
    override fun onReceive(context: Context, intent: Intent) {
        val i = Intent(context, NotificationService::class.java)
        context.startService(i)
    }

    companion object {
        val REQUEST_CODE = 12345
    }
}