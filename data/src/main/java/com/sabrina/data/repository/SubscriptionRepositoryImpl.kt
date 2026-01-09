package com.sabrina.data.repository

import com.sabrina.data.local.dao.SubscriptionDao
import com.sabrina.data.mapper.toDomain
import com.sabrina.data.mapper.toEntity
import com.sabrina.domain.model.CategorySum
import com.sabrina.domain.model.SubscriptionModel
import com.sabrina.domain.repository.SubscriptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubscriptionRepositoryImpl @Inject constructor(
    private val dao: SubscriptionDao
) : SubscriptionRepository{
    override fun getAllSubscriptions(): Flow<List<SubscriptionModel>> {
        return dao.getAllSubscriptions().map { list->
            list.map { it.toDomain() }
        }
    }

    override fun getSpendingByCategory(): Flow<List<CategorySum>> {
        return dao.getSpendingByCategory()
    }

    override suspend fun insertSubscription(subscription: SubscriptionModel) {
        return dao.insertSubscription(subscription.toEntity())
    }

    override suspend fun deleteSubscription(subscription: SubscriptionModel) {
        return dao.deleteSubscription(subscription.toEntity())
    }

}