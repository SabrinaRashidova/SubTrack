package com.sabrina.subtrack.ui.screens.add_subscription

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.sabrina.domain.model.SubscriptionModel
import com.sabrina.domain.repository.SubscriptionRepository
import com.sabrina.subtrack.utils.ReminderWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class AddSubscriptionViewModel @Inject constructor(
    private val repository: SubscriptionRepository,
    @ApplicationContext private val context: Context
) : ViewModel(){

    var name by mutableStateOf("")
    var cost by mutableStateOf("")
    var category by mutableStateOf("Entertainment")
        private set

    fun onCategoryChange(newCategory: String) {
        category = newCategory
    }

    var billingDate by mutableLongStateOf(System.currentTimeMillis())

    fun saveSubscription(onSuccess: () -> Unit){
        val costDouble = cost.toDoubleOrNull() ?: 0.0
        if (name.isBlank() || costDouble <= 0.0) return

        viewModelScope.launch {
            repository.insertSubscription(
                SubscriptionModel(
                    id = 0,
                    name = name,
                    monthlyCost = costDouble,
                    billingDate = billingDate,
                    category = category,
                    notes = ""
                )
            )
            scheduleReminderWorker()

            onSuccess()
        }
    }

    private fun scheduleReminderWorker() {
        val workRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(5, TimeUnit.SECONDS)
            .build()

        WorkManager
            .getInstance(context)
            .enqueue(workRequest)
    }

}