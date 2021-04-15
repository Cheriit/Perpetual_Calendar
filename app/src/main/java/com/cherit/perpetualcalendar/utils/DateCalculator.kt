package com.cherit.perpetualcalendar.utils

import com.cherit.perpetualcalendar.models.Event
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.ArrayList

fun getShoppingSundayList(year: Int): ArrayList<Event> {
    val shoppingSundaysList = ArrayList<Event>()

    for (month: Int in arrayListOf(1, 4, 6, 8)) {
        var endOfMonth = getLastDayOfMonth(year, month)
        if (endOfMonth.dayOfWeek != DayOfWeek.SUNDAY)
            endOfMonth = getPreviousSunday(endOfMonth)
        shoppingSundaysList.add(Event("Niedziela handlowa", convertLocalDateToDate(endOfMonth), "Koniec miesiąca"))
    }

    val sundayBeforeChristmas = getPreviousSunday(LocalDate.of(year, 12, 25))

    shoppingSundaysList.add(Event(
            "Niedziela handlowa",
            convertLocalDateToDate(sundayBeforeChristmas),
            "Niedziela przed Bożym Narodzeniem"
    ))
    shoppingSundaysList.add(Event(
            "Niedziela handlowa",
            convertLocalDateToDate(sundayBeforeChristmas.minusWeeks(1)),
            "Niedziela przed Bożym Narodzeniem"
    ))
    shoppingSundaysList.add(Event(
            "Niedziela handlowa",
            convertLocalDateToDate(getPreviousSunday(getEasterDate(year))),
            "Niedziela przed wielkanocą"
    ))
    return ArrayList(shoppingSundaysList.sortedWith(compareBy { it.date }))
}

fun getEasterDate(year: Int): LocalDate {
    val a: Int = year % 19
    val b: Int = year / 100
    val c: Int = year % 100
    val d: Int = b / 4
    val e: Int = b % 4
    val f: Int = ((b + 8) / 25)
    val g: Int = ((b - f + 1) / 3)
    val h: Int = (19 * a + b - d - g + 15) % 30
    val i: Int = c / 4
    val k: Int = c % 4
    val l: Int = (32 + 2 * e + 2 * i - h - k) % 7
    val m: Int = ((a + 11 * h + 22 * l) / 451)
    val p: Int = (h + l - 7 * m + 114) % 31

    return LocalDate.of(year, ((h + l - 7 * m + 114) / 31), p + 1)
}

fun getMovingEvents(year: Int): ArrayList<Event> {
    val easterDate = getEasterDate(year)

    val christmasDate = LocalDate.of(year, 12, 25)

    val adventDate = getPreviousSunday(christmasDate)
            .minusWeeks(3)

    return arrayListOf(
            Event("Wielkanoc", convertLocalDateToDate(easterDate)),
            Event("Popielec", convertLocalDateToDate(easterDate.minusDays(46))),
            Event("Boże ciało", convertLocalDateToDate(easterDate.plusDays(60))),
            Event("Adwent", convertLocalDateToDate(adventDate))
    )
}

fun getLastDayOfMonth(year: Int, month: Int): LocalDate {
    val date = LocalDate.of(year, month, 1)
    return date.withDayOfMonth(date.month.length(date.isLeapYear))
}

fun getPreviousSunday(date: LocalDate): LocalDate {
    return date.minusDays(date.dayOfWeek.value.toLong())
}

fun getDateDifferential(fromDate: LocalDate, toDate: LocalDate): Pair<Int, Int> {
    if (fromDate > toDate)
        return Pair(-1, -1)

    val dateDiff = Duration.between(fromDate.atStartOfDay(), toDate.atStartOfDay()).toDays().toInt()

    var freeDays = getWeekDaysBetween(fromDate, toDate)

    val freeEvents = arrayListOf(
            Pair(1, 1),
            Pair(1, 6),
            Pair(5, 1),
            Pair(5, 3),
            Pair(8, 15),
            Pair(11, 1),
            Pair(11, 11),
            Pair(12, 25),
            Pair(12, 26)
    )
    for (i in fromDate.year..toDate.year) {
        for (date: Pair<Int, Int> in freeEvents)
        {
            val eventDate = LocalDate.of(i, date.first, date.second)
            if (isDateBetween(fromDate, toDate, eventDate) && !isDateWeekend(eventDate))
                freeDays += 1
        }
        val easterNextDate = getEasterDate(i).plusDays(1)
        if (isDateBetween(fromDate, toDate, easterNextDate) && !isDateWeekend(easterNextDate))
            freeDays += 1

        val divineBodyDate = getEasterDate(i).plusDays(60)
        if (isDateBetween(fromDate, toDate, divineBodyDate) && !isDateWeekend(divineBodyDate))
            freeDays += 1

    }
    return Pair(dateDiff, dateDiff-freeDays)
}

private fun getWeekDaysBetween(fromDate: LocalDate, toDate: LocalDate): Int {
    var freeDays = (ChronoUnit.WEEKS.between(getNextMonday(fromDate), getNextMonday(toDate)) + 1).toInt() * 2

    if (fromDate.dayOfWeek == DayOfWeek.SUNDAY)
        freeDays -= 1

    if (toDate.dayOfWeek == DayOfWeek.SATURDAY)
        freeDays -= 1
    else if (toDate.dayOfWeek < DayOfWeek.SATURDAY)
        freeDays -= 2

    return freeDays
}

fun getNextMonday(date: LocalDate): LocalDate {
    return date.plusDays((7 - date.dayOfWeek.value).toLong())
}

fun convertLocalDateToDate(dateToConvert: LocalDate): Date {
    return Date.from(dateToConvert.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant())
}

fun isDateBetween(fromDate: LocalDate, toDate: LocalDate, targetDate: LocalDate): Boolean {
    return targetDate in fromDate..toDate
}

fun isDateWeekend(date: LocalDate): Boolean {
    return date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
}