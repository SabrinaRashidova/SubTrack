package com.sabrina.subtrack.di

import android.content.Context
import androidx.room.Room
import com.sabrina.data.local.dao.SubscriptionDao
import com.sabrina.data.local.db.SubDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SubDatabase{
        return Room.databaseBuilder(
            context,
            SubDatabase::class.java,
            "subtrack_db"
        ).build()
    }

    @Provides
    fun provideSubscriptionDao(database: SubDatabase) : SubscriptionDao{
        return database.subscriptionDao()
    }
}