package com.sabrina.subtrack.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatLongToDate(displayTime: Long): String{
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.getDefault())

    return Instant.ofEpochMilli(displayTime)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(formatter)
}

fun formatTimestamp(timestamp: Long): String{
    val date = Date(timestamp)
    val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    return formatter.format(date)
}

fun getNextBillingDate(billingTimestamp: Long): String{
    val billingCalendar = Calendar.getInstance().apply { timeInMillis = billingTimestamp }
    val today = Calendar.getInstance()

    if (billingCalendar.before(today)) {
        billingCalendar.add(Calendar.MONTH, 1)
    }

    val formatter = java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault())
    return formatter.format(billingCalendar.time)
}