package com.courseworm.yoga.database


import androidx.room.TypeConverter
import com.courseworm.yoga.database.entities.options.DayOfWeek
import com.courseworm.yoga.database.entities.options.Expertise
import java.util.Date

class Converters {

    // Convert List<Expertise> to a single String (comma-separated)
    @TypeConverter
    fun fromExpertiseList(expertise: List<Expertise>): String {
        return expertise.joinToString(separator = ",") { it.name }
    }

    // Convert String back to List<Expertise>
    @TypeConverter
    fun toExpertiseList(data: String): List<Expertise> {
        return data.split(",").map { Expertise.valueOf(it) }
    }



    // Converter for Date to Long (timestamp)
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    // Converter from Long (timestamp) to Date
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun fromDayOfWeekList(days: List<DayOfWeek>): String {
        return days.joinToString(separator = ",") { it.name }
    }

    @TypeConverter
    fun toDayOfWeekList(data: String): List<DayOfWeek> {
        return data.split(",").map { DayOfWeek.valueOf(it) }
    }





}