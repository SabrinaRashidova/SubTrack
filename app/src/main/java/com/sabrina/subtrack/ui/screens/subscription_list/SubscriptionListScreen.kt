package com.sabrina.subtrack.ui.screens.subscription_list

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionListScreen(
    viewModel: SubscriptionListViewModel = hiltViewModel(),
    onNavigateToAdd: () -> Unit
){
    val state by viewModel.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text("My Subscriptions") },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAdd,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Subscription")
            }
        }
    ) {paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TotalSpendingCard(
                subscriptions = state.subscriptions,
                total = state.totalMonthlyCost
            )

            if (state.subscriptions.isEmpty()){
                EmptyStateView(
                    onAddComponentClick = onNavigateToAdd
                )
            }else{
                LazyColumn {
                    items(
                        items = state.subscriptions,
                        key = { it.id }
                    ){sub->
                        val dismissState = rememberSwipeToDismissBoxState(
                            confirmValueChange = { value->
                                if (value == SwipeToDismissBoxValue.EndToStart){
                                    viewModel.deleteSubscription(sub)
                                    true
                                } else false
                            }
                        )

                        SwipeToDismissBox(
                            state = dismissState,
                            enableDismissFromStartToEnd = false,
                            backgroundContent = {
                                val color by animateColorAsState(
                                    when(dismissState.targetValue){
                                        SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.errorContainer
                                        else -> Color.Transparent
                                    }, label = "color"
                                )

                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .clip(RoundedCornerShape(24.dp))
                                    .background(color),
                                    contentAlignment = Alignment.CenterEnd){
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        modifier = Modifier.padding(end = 24.dp),
                                        tint = MaterialTheme.colorScheme.onErrorContainer
                                    )
                                }
                            }
                        ) {
                            SubscriptionItem(
                                modifier = Modifier.animateItem(
                                    fadeInSpec = tween(300),
                                    fadeOutSpec = tween(300),
                                    placementSpec = spring(
                                        dampingRatio = Spring.DampingRatioMediumBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                ),
                                subscription = sub,
                                onDelete = { viewModel.deleteSubscription(sub)}
                            )
                        }
                    }
                }
            }
        }
    }
}










