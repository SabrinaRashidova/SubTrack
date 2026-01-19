package com.sabrina.subtrack.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatLongToDate(displayTime: Long): String{
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.getDefault())

    return Instant.ofEpochMilli(displayTime)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(formatter)
}