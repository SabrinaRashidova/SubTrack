package com.sabrina.subtrack.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sabrina.domain.model.SubscriptionModel
import com.sabrina.domain.repository.SubscriptionRepository
import com.sabrina.subtrack.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

@HiltWorker
class ReminderWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: SubscriptionRepository
) : CoroutineWorker(context,params){

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        val subscriptions = repository.getAllSubscriptions().first()

        val currentTime = System.currentTimeMillis()
        val twentyFourHoursFromNow = currentTime + (24 * 60 * 60 * 1000)

        val upcoming = subscriptions.filter { it.billingDate in currentTime..twentyFourHoursFromNow }
        upcoming.forEach { sub ->
            showNotification(sub)
        }

        return Result.success()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification(subscription: SubscriptionModel){
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel("reminders","Payment Reminders", NotificationManager.IMPORTANCE_HIGH)
        manager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(applicationContext,"reminders")
            .setContentTitle("Subscription Due Tomorrow!")
            .setContentText("${subscription.name} is about to renew for $${subscription.monthlyCost}")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        manager.notify(subscription.id, notification)
    }
}