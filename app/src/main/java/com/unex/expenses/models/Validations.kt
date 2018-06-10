package com.unex.expenses.models

object Validations {

    class EmptyAmountException : Exception()

    fun parseAmount(amount: String): Int {
        if (amount.isEmpty()) throw EmptyAmountException()
        return amount.toInt()
    }
}
