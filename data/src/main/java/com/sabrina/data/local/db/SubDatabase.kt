package com.sabrina.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sabrina.data.local.dao.SubscriptionDao
import com.sabrina.data.local.entity.Subscription

@Database(entities = [Subscription::class], version = 1, exportSchema = true)
abstract class SubDatabase : RoomDatabase(){
    abstract fun subscriptionDao(): SubscriptionDao
}