package com.unex.expenses.models

object Validations {

    fun validateAmount(value: String): String {
        if (value.isEmpty()) {
            throw Exception("String is empty")
        } else {
            return value
        }
    }
}
