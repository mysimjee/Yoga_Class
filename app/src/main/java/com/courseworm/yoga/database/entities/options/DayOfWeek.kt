package com.courseworm.yoga.database.entities.options

import android.icu.util.Calendar

enum class DayOfWeek {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

    fun getCalendarDay(): Int {
        return when (this) {
            SUNDAY -> Calendar.SUNDAY
            MONDAY -> Calendar.MONDAY
            TUESDAY -> Calendar.TUESDAY
            WEDNESDAY -> Calendar.WEDNESDAY
            THURSDAY -> Calendar.THURSDAY
            FRIDAY -> Calendar.FRIDAY
            SATURDAY -> Calendar.SATURDAY
        }
    }
}
