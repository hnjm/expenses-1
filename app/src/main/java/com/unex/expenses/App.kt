package com.unex.expenses

import android.app.Application
import com.unex.expenses.persistence.Database
import com.unex.expenses.utils.NotificationUtils

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Database.setUp(applicationContext)

        NotificationUtils.scheduleAlarm(applicationContext)
    }
}
