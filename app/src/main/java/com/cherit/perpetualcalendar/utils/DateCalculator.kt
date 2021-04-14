package com.cherit.perpetualcalendar.utils

import com.cherit.perpetualcalendar.models.Event
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

fun getShoppingSundayList(year: Int): ArrayList<Event> {
    return arrayListOf(
        Event("Niedziela " + year, Date()),
        Event("Niedziela " + year, Date()),
        Event("Niedziela " + year, Date()),
        Event("Niedziela " + year, Date())
    )
}

fun getMovingEvents(year: Int): ArrayList<Event> {
    return arrayListOf(
        Event(name = "Popielec", date = Date()),
        Event(name = "Wielkanoc", date = Date()),
        Event(name = "Boże ciało", date = Date()),
        Event(name = "Adwent", date = Date())
    )
}

fun getDateDifferential(fromDate: LocalDate, toDate: LocalDate): Int {
    if (fromDate > toDate)
        return -1
    return 0
}