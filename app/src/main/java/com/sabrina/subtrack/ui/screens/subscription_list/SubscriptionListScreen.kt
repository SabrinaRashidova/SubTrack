package com.sabrina.subtrack.ui.screens.subscription_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SubscriptionListScreen(
    viewModel: SubscriptionListViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()
}