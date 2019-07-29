package com.mlcorrea.stackeruser.framework.extension

import android.text.format.DateFormat
import java.util.*

/**
 * Created by manuel on 29/07/19
 */
fun Long.parseToDateSting(): String {
    val cal = Calendar.getInstance(Locale.getDefault())
    cal.timeInMillis = this * 1000L
    return DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString()
}