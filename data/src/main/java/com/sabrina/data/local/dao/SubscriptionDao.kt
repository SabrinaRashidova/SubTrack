package com.sabrina.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sabrina.data.local.entity.Subscription
import com.sabrina.domain.model.CategorySum
import kotlinx.coroutines.flow.Flow

interface SubscriptionDao {
    @Query("SELECT * FROM subscriptions ORDER BY billingDate ASC")
    fun getAllSubscriptions(): Flow<List<Subscription>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscription(subscription: Subscription)

    @Delete
    suspend fun deleteSubscription(subscription: Subscription)

    @Query("SELECT category, SUM(monthlyCost) as total FROM subscriptions GROUP BY category")
    fun getSpendingByCategory(): Flow<List<CategorySum>>
}