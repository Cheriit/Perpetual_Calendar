package com.cherit.perpetualcalendar.models

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

data class Event(var name: String, var date: Date, var description: String = "") {

    fun getDate(): String {
        return DateFormat.getDateInstance().format(date)
    }

}