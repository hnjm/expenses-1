package com.unex.expenses.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.unex.expenses.R
import com.unex.expenses.ui.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, HomeFragment())
                .commit()
    }
}
