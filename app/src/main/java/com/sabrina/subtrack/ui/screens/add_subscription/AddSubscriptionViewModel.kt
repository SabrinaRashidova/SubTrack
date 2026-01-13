package com.sabrina.subtrack.ui.screens.add_subscription

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabrina.domain.model.SubscriptionModel
import com.sabrina.domain.repository.SubscriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSubscriptionViewModel @Inject constructor(
    private val repository: SubscriptionRepository
) : ViewModel(){

    var name by mutableStateOf("")
    var cost by mutableStateOf("")
    var category by mutableStateOf("Entertainment")

    fun saveSubscription(onSuccess: () -> Unit){
        val costDouble = cost.toDoubleOrNull() ?: 0.0
        if (name.isBlank() || costDouble <= 0.0) return

        viewModelScope.launch {
            repository.insertSubscription(
                SubscriptionModel(
                    id = 0,
                    name = name,
                    monthlyCost = costDouble,
                    billingDate = System.currentTimeMillis(),
                    category = category,
                    notes = ""
                )
            )
            onSuccess
        }
    }
}