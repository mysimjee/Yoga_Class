package com.courseworm.yoga.database.entities

import android.util.Log
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(
    tableName = "ClassDates",
    foreignKeys = [
        ForeignKey(
            entity = Schedule::class,
            parentColumns = ["scheduleId"],
            childColumns = ["scheduleId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ClassDate(
    @PrimaryKey val classDateId: UUID = UUID.randomUUID(), // Unique ID for the class date
    val scheduleId: UUID, // Foreign key linking to Schedule
    val date: Date // The actual date of the class
)

// Function to convert ClassDate object to Map
fun ClassDate.toMap(): Map<String, Any?> {
    return try {
        val map = mapOf(
            "classDateId" to classDateId.toString(),
            "scheduleId" to scheduleId.toString(),
            "date" to date.time // Convert Date to timestamp
        )
        Log.d("ClassDateExtension", "Converted ClassDate to Map: $map")
        map
    } catch (e: Exception) {
        Log.e("ClassDateExtension", "Error converting ClassDate to Map: ${e.message}", e)
        emptyMap() // Return an empty map in case of error
    }
}


