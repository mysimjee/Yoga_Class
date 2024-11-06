package com.courseworm.yoga.database.entities


import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.courseworm.yoga.database.entities.options.Expertise
import java.util.Date
import java.util.UUID


@Entity(tableName = "Instructors")
data class Instructor(
    @PrimaryKey val instructorId: UUID = UUID.randomUUID(), // Using UUID for the ID,
    val name: String,
    val bio: String? = null, // Optional biography
    val birthdate: Date?,
    val profilePictureUrl: String? = null, // URL or path to the profile picture
    val experienceYears: Int, // Number of years of experience
    val expertise: List<Expertise> // List of expertise areas

)  {
    override fun toString(): String {
        return name // Display only the name in the spinner
    }
}

// Function to convert Instructor object to Map
fun Instructor.toMap(): Map<String, Any?> {
    return try {
        val map = mapOf(
            "instructorId" to instructorId.toString(),
            "name" to name,
            "bio" to bio,
            "birthdate" to birthdate?.time, // Convert Date to timestamp
            "profilePictureUrl" to profilePictureUrl, // Convert Uri to String
            "experienceYears" to experienceYears,
            "expertise" to expertise.map { it.name } // Expertise is an enum
        )
        Log.d("InstructorExtension", "Converted Instructor to Map: $map")
        map
    } catch (e: Exception) {
        Log.e("InstructorExtension", "Error converting Instructor to Map: ${e.message}", e)
        emptyMap() // Return an empty map in case of error
    }
}
