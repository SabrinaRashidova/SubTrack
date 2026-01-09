package com.sabrina.subtrack.ui.screens.subscription_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabrina.domain.model.SubscriptionModel
import com.sabrina.domain.repository.SubscriptionRepository
import com.sabrina.subtrack.ui.screens.SubscriptionListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscriptionListViewModel @Inject constructor(
    private val repository: SubscriptionRepository
) : ViewModel(){

    private val _state = MutableStateFlow(SubscriptionListState())
    val state: StateFlow<SubscriptionListState> = _state.asStateFlow()

    init {

    }

    private fun loadSubscriptions(){
        repository.getAllSubscriptions()
            .onEach { subs->
                _state.update { it.copy(
                    subscriptions = subs,
                    totalMonthlyCost = subs.sumOf { it.monthlyCost }
                ) }
            }.launchIn(viewModelScope)
    }

    fun deleteSubscription(subscription: SubscriptionModel){
        viewModelScope.launch {
            repository.deleteSubscription(subscription)
        }
    }
}