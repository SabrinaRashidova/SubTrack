package com.sabrina.subtrack.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.sabrina.domain.model.SubscriptionModel

@Composable
fun SpendingPieChart(
    subscriptions: List<SubscriptionModel>,
    modifier: Modifier = Modifier
){
    val categoryTotals = subscriptions.groupBy { it.category }
        .mapValues { it.value.sumOf { sub -> sub.monthlyCost }.toFloat() }

    val totalSpend = categoryTotals.values.sum()

    val animationProgress = remember { Animatable(0f) }
    LaunchedEffect(subscriptions) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing)
        )
    }

    val rotation by animateFloatAsState(
        targetValue = totalSpend.toFloat(),
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "chartRotation"
    )

    Canvas(modifier = modifier.size(100.dp).graphicsLayer(rotationZ = rotation)) {
        var startAngle = -90f

        categoryTotals.forEach { (category,amount) ->
            val sweepAngle = (amount / totalSpend) * 360f * animationProgress.value

            drawArc(
                color = getCategoryColor(category),
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 30f, cap = StrokeCap.Round)
            )
            startAngle += sweepAngle
        }
    }
}

fun getCategoryColor(category: String) : Color{
    return when(category) {
        "Entertainment" -> Color(0xFF673AB7)
        "Food" -> Color(0xFF4CAF50)
        "Utilities" -> Color(0xFFFF9800)
        "Health" -> Color(0xFF03A9F4)
        else -> Color(0xFF9E9E9E)
    }
}