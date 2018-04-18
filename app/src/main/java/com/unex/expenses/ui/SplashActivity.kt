package com.unex.expenses.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.unex.expenses.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        val database = Database.get()
//        AsyncTask.execute({
//            database.runInTransaction {
//                DataGenerator.generateSpendings().forEach { spending ->
//                    database.spendingDao().insert(spending)
//                }
//            }
//        })
    }
}