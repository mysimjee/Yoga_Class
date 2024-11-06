package com.courseworm.yoga.database.entities

import android.icu.util.Calendar
import android.util.Log
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.courseworm.yoga.database.entities.options.DayOfWeek
import java.util.Date
import java.util.UUID



@Entity(
    tableName = "Schedules",
    foreignKeys = [
        ForeignKey(
            entity = Course::class,
            parentColumns = ["courseId"],
            childColumns = ["courseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["courseId"])] // Indexing for foreign key
)
data class Schedule(
    @PrimaryKey val scheduleId: UUID = UUID.randomUUID(), // Using UUID for the ID
    val courseId: UUID, // Foreign key linking to Course
    val startDate: Date = Date(), // Start date of the schedule (e.g., "yyyy-MM-dd")
    val endDate: Date = Date(), // End date of the schedule (e.g., "yyyy-MM-dd")
    val startTime: String? = null, // Start time of the schedule as String (e.g., "HH:mm")
    val endTime: String? = null, // End time of the schedule as String (e.g., "HH:mm")
    val daysOfWeek: List<DayOfWeek> = listOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY), // List of days of the week
    val description: String? = null, // Optional description of the schedule
    val classId: UUID,
    val instructorId: UUID
)  {
    companion object {
        fun calculateClassDates(startDate: Date, endDate: Date, daysOfWeek: List<DayOfWeek>): List<Date> {
            val classDates = mutableListOf<Date>()
            val calendar = Calendar.getInstance().apply {
                time = startDate
            }

            // Iterate through each day in the range
            while (calendar.time <= endDate) {
                // Get the current day of the week
                val currentDayOfWeek = DayOfWeek.entries[calendar.get(Calendar.DAY_OF_WEEK) - 1]

                // Check if the current day is in the specified daysOfWeek
                if (currentDayOfWeek in daysOfWeek) {
                    classDates.add(calendar.time)
                }

                // Move to the next day
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }

            return classDates
        }
    }
}
//
//// Function to convert Schedule object to Map
//fun Schedule.toMap(): Map<String, Any?> {
//    return mapOf(
//        "scheduleId" to scheduleId.toString(),
//        "courseId" to courseId.toString(),
//        "startDate" to startDate.time, // Convert Date to timestamp
//        "endDate" to endDate.time, // Convert Date to timestamp
//        "startTime" to startTime, // Keep start time as String
//        "endTime" to endTime, // Keep end time as String
//        "daysOfWeek" to daysOfWeek.map { it.name }, // Assuming DayOfWeek is an enum
//        "description" to description.toString(),
//        "classId" to classId.toString(), // Convert classId to String
//        "instructorId" to instructorId.toString()
//    )
//}

// Function to convert Schedule object to Map
fun Schedule.toMap(): Map<String, Any?> {
    return try {
        val map = mapOf(
            "scheduleId" to scheduleId.toString(),
            "courseId" to courseId.toString(),
            "startDate" to startDate.time, // Convert Date to timestamp
            "endDate" to endDate.time, // Convert Date to timestamp
            "startTime" to startTime, // Keep start time as String
            "endTime" to endTime, // Keep end time as String
            "daysOfWeek" to daysOfWeek.map { it.name }, // Assuming DayOfWeek is an enum
            "description" to description.toString(),
            "classId" to classId.toString(), // Convert classId to String
            "instructorId" to instructorId.toString()
        )
        Log.d("ScheduleExtension", "Converted Schedule to Map: $map")
        map
    } catch (e: Exception) {
        Log.e("ScheduleExtension", "Error converting Schedule to Map: ${e.message}", e)
        emptyMap() // Return an empty map in case of error
    }
}
