package com.nursultan.cryptoapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun convertFromTimestampToTime(timestamp: Long?): String {
    if (timestamp==null)return ""
    val date = Date(timestamp*1000)
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)

}