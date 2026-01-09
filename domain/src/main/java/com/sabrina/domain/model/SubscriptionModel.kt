package com.sabrina.domain.model

data class SubscriptionModel(
    val id: Int,
    val name: String,
    val monthlyCost: Double,
    val billingDate: Long,
    val category: String,
    val notes: String
)
