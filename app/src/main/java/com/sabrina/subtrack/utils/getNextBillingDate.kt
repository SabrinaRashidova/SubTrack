package com.sabrina.subtrack.utils

import java.util.Calendar

fun getNextBillingDate(billingTimestamp: Long): String{
    val billingCalendar = Calendar.getInstance().apply { timeInMillis = billingTimestamp }
    val today = Calendar.getInstance()

    if (billingCalendar.before(today)) {
        billingCalendar.add(Calendar.MONTH, 1)
    }

    val formatter = java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault())
    return formatter.format(billingCalendar.time)
}