package com.example.keepfitapp.domain.function

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

//获取今天日期的时间戳，去掉分秒
fun getTodayTimestamp(): Long {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date().time))!!.time
}

//时间戳转换为日期
fun timeStampToDate(timestamp: Long): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(timestamp)
}

//LocalDate to timestamp
@RequiresApi(Build.VERSION_CODES.O)
fun localdateToTimestamp(time: LocalDate): Long {
    return time.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
}
