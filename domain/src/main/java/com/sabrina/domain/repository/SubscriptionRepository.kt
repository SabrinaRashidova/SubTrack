package com.sabrina.domain.repository

import com.sabrina.domain.model.CategorySum
import com.sabrina.domain.model.SubscriptionModel
import kotlinx.coroutines.flow.Flow

interface SubscriptionRepository {
    fun getAllSubscriptions(): Flow<List<SubscriptionModel>>
    fun getSpendingByCategory(): Flow<List<CategorySum>>
    suspend fun insertSubscription(subscription: SubscriptionModel)
    suspend fun deleteSubscription(subscription: SubscriptionModel)
}