package com.courseworm.yoga.database.entities


import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.courseworm.yoga.database.entities.options.ClassType
import com.courseworm.yoga.database.entities.options.DayOfWeek
import com.courseworm.yoga.database.entities.options.Level
import java.util.Date
import java.util.UUID
import kotlin.reflect.typeOf


@Entity(tableName = "Courses")

data class Course(
    @PrimaryKey val courseId: UUID = UUID.randomUUID(), // Using UUID for the ID,
    val courseName: String,
    val duration: Int, // Duration in minutes
    val capacity: Int,
    val level: Level, // e.g., Beginner, Intermediate, Advanced
    val price: Double, // Price in your preferred currency
    val startDate: Date = Date(), // Start date in a suitable format (e.g., "yyyy-MM-dd")
    val endDate: Date = Date(), // End date in a suitable format
    val pictureUrl: String?, // URL or path to the course image
    val description: String?,// Optional description
    val classType: ClassType,
    val startTime: String, // Start time in "HH:mm" format
    val endTime: String, // End time in "HH:mm" format
    val daysOfWeek: List<DayOfWeek> // List of days of the week
) {
    override fun toString(): String {
        return courseName // Display only the name in the spinner
    }
}


// Function to convert Course object to Map

fun Course.toMap(): Map<String, Any?> {
    return try {
        val map = mapOf(
            "courseId" to courseId.toString(),
            "courseName" to courseName,
            "duration" to duration,
            "level" to level.name, // Assuming Level is an enum
            "price" to price,
            "capacity" to capacity,
            "startDate" to startDate.time, // Convert Date to timestamp
            "endDate" to endDate.time, // Convert Date to timestamp
            "pictureUrl" to pictureUrl, // Convert Uri to String
            "description" to description?.toString(),
            "startTime" to startTime, // Include start time
            "endTime" to endTime, // Include end time
            "classType" to classType.name,
            "daysOfWeek" to daysOfWeek.map { it.name } // Convert List<DayOfWeek> to List<String>
        )
        Log.d("CourseExtension", "Converted Course to Map: $map")
        map
    } catch (e: Exception) {
        Log.e("CourseExtension", "Error converting Course to Map: ${e.message}", e)
        emptyMap() // Return an empty map in case of error
    }
}


