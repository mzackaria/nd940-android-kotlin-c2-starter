package com.udacity.asteroidradar.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Calendar
import kotlin.collections.ArrayList

@SuppressLint("ConstantLocale")
private var dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())

fun getNextSevenDaysFormattedDates(): ArrayList<String> {
    val formattedDateList = ArrayList<String>()

    val calendar = Calendar.getInstance()
    for (i in 0..Constants.DEFAULT_END_DATE_DAYS) {
        val currentTime = calendar.time
        formattedDateList.add(dateFormat.format(currentTime))
        calendar.add(Calendar.DAY_OF_YEAR, 1)
    }

    return formattedDateList
}

fun getTodayDateFormatted() : String {
    val calendar = Calendar.getInstance()
    return dateFormat.format(calendar.time)
}

fun getLastDateFormatted() : String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, Constants.DEFAULT_END_DATE_DAYS)
    return dateFormat.format(calendar.time)
}
