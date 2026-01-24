package com.sabrina.subtrack.ui.screens.subscription_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sabrina.domain.model.SubscriptionModel
import com.sabrina.subtrack.ui.screens.AnimatedTotal
import com.sabrina.subtrack.ui.screens.SpendingPieChart

@Composable
fun TotalSpendingCard(subscriptions: List<SubscriptionModel>, total: Double){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(28.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
        )
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            if (subscriptions.isNotEmpty()) {
                SpendingPieChart(subscriptions = subscriptions)
            }

            Column {
                Text(
                    text = "Total Monthly Spending",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )

                AnimatedTotal(total = total)

//                AnimatedContent(
//                    targetState = total,
//                    transitionSpec = {
//                        fadeIn(animationSpec = tween(500)) togetherWith fadeOut(animationSpec = tween(500))
//                    },label = "priceAnimation"
//                ) {targetGoal->
//                    Text(
//                        text = "$${String.format("%.2f", targetGoal)}",
//                        style = MaterialTheme.typography.displayMedium,
//                        fontWeight = FontWeight.ExtraBold,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//                }
            }
        }
    }
}