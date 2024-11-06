package com.courseworm.yoga.database.entities

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.courseworm.yoga.database.entities.options.ClassType
import com.courseworm.yoga.database.entities.options.Level
import java.util.UUID


@Entity(tableName = "YogaClasses")
data class YogaClass(
    @PrimaryKey val classId: UUID = UUID.randomUUID(), // Using UUID for the ID,
    val className: String,
    val description: String? = null, // Optional description
    val pictureUrl: String? = null, // URL or path to the class image
    val capacity: Int, // Maximum number of participants
    val classType: ClassType, // Type of class (e.g., Flow Yoga, Aerial Yoga)
    val skillLevel: Level? = null, // Optional skill level (e.g., Beginner, Intermediate, Advanced)
    val pricePerClass: Double // Price per class (e.g., £10)
)  {
    override fun toString(): String {
        return className // Display only the name in the spinner
    }
}

//// Function to convert YogaClass object to Map
//fun YogaClass.toMap(): Map<String, Any?> {
//    return mapOf(
//        "classId" to classId.toString(),
//        "className" to className,
//        "description" to description,
//        "pictureUrl" to pictureUrl,
//        "capacity" to capacity,
//        "classType" to classType.name,
//        "skillLevel" to skillLevel?.name,
//        "pricePerClass" to pricePerClass
//    )
//}
//
// Function to convert YogaClass object to Map
fun YogaClass.toMap(): Map<String, Any?> {
    return try {
        val map = mapOf(
            "classId" to classId.toString(),
            "className" to className,
            "description" to description,
            "pictureUrl" to pictureUrl,
            "capacity" to capacity,
            "classType" to classType.name,
            "skillLevel" to skillLevel?.name, // Handle nullable skillLevel
            "pricePerClass" to pricePerClass
        )
        Log.d("YogaClassExtension", "Converted YogaClass to Map: $map")
        map
    } catch (e: Exception) {
        Log.e("YogaClassExtension", "Error converting YogaClass to Map: ${e.message}", e)
        emptyMap() // Return an empty map in case of error
    }
}
