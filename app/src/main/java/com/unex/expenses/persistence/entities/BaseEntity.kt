package com.unex.expenses.persistence.entities

import android.arch.persistence.room.PrimaryKey

open class BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
