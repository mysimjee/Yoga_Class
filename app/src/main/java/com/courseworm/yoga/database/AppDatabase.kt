package com.courseworm.yoga.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import androidx.room.TypeConverters

import com.courseworm.yoga.database.entities.ClassDate


import com.courseworm.yoga.database.entities.Course

import com.courseworm.yoga.database.entities.Instructor
import com.courseworm.yoga.database.entities.Schedule
import com.courseworm.yoga.database.entities.YogaClass


@Database(
    entities = [
        YogaClass::class,
        Course::class,
        Schedule::class,
        ClassDate::class,
        Instructor::class,
    ],
    version = 1,
    exportSchema = true
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getYogaDao(): DAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "yoga_database"
        private const val TAG = "AppDatabase"

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                try {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                    INSTANCE = instance
                    instance
                } catch (e: Exception) {
                    Log.e(TAG, "Error creating database: ${e.message}", e)
                    throw e // Rethrow the exception to notify callers
                }
            }
        }
    }
}
