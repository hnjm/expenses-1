package com.unex.expenses.models

interface Spending {

    fun getAmount(): Double
    fun getTags(): List<String>
    fun getDescription(): String
}