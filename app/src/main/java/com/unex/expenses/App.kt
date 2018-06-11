package com.unex.expenses

import android.app.Application
import com.unex.expenses.persistence.Database

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        with(applicationContext) {
            Database.setUp(this)
//            Notifications.scheduleAlarm(this)
        }
    }
}
