package com.sabrina.data.mapper

import com.sabrina.data.local.entity.Subscription
import com.sabrina.domain.model.SubscriptionModel

fun Subscription.toDomain() : SubscriptionModel {
    return SubscriptionModel(
        id = id,
        name = name,
        monthlyCost = monthlyCost,
        billingDate = billingDate,
        category = category,
        notes = notes
    )
}

fun SubscriptionModel.toEntity(): Subscription {
    return Subscription(id, name, monthlyCost, billingDate, category, notes = notes)
}