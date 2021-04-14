package com.cherit.perpetualcalendar.utils

import com.cherit.perpetualcalendar.models.Event
import java.util.*
import kotlin.collections.ArrayList

fun getShoppingSundayList(year: Int): List<Event> {
    return ArrayList()
}

fun getChristmas(year: Int): Event {
    return Event(name = "Test", date = Date())
}

fun getDateDifferential(fromDate: Date, toDate: Date): Int {
    return 0;
}