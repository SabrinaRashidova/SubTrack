package com.sabrina.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriptions")
data class Subscription(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val monthlyCost: Double,
    val billingDate: Long,
    val category: String,
    val iconResId: Int? = null,
    val notes: String = ""
)