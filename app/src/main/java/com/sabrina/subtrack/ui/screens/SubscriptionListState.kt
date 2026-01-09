package com.sabrina.subtrack.ui.screens

import com.sabrina.domain.model.SubscriptionModel

data class SubscriptionListState(
    val subscriptions: List<SubscriptionModel> = emptyList(),
    val isLoading: Boolean = false,
    val totalMonthlyCost: Double = 0.0
)
