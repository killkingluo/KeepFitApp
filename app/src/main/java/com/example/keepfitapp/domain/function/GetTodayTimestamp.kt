package com.example.keepfitapp.domain.function

import java.text.SimpleDateFormat
import java.util.*

//获取今天日期的时间戳，去掉分秒
fun getTodayTimestamp(): Long {
    return SimpleDateFormat("yyyy-MM-dd").parse(SimpleDateFormat("yyyy-MM-dd").format(Date().time))!!.time
}