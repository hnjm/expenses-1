package com.unex.expenses.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.unex.expenses.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, SpendingListFragment())
                .commit()
    }
}
