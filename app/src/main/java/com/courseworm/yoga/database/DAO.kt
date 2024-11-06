package com.courseworm.yoga.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.courseworm.yoga.database.entities.ClassDate


import com.courseworm.yoga.database.entities.Course

import com.courseworm.yoga.database.entities.Instructor

import com.courseworm.yoga.database.entities.Schedule
import com.courseworm.yoga.database.entities.YogaClass

import java.util.UUID


@Dao
interface DAO {

    // Insert a new course into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Course)

    // Insert multiple courses
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<Course>)

    // Update an existing course
    @Update
    suspend fun updateCourse(course: Course)

    // Delete a course
    @Delete
    suspend fun deleteCourse(course: Course)

    // Query to get all courses
    @Query("SELECT * FROM Courses")
    suspend fun getAllCourses(): List<Course>

    // Query to get a course by its ID
    @Query("SELECT * FROM Courses WHERE courseId = :courseId")
    suspend fun getCourseById(courseId: UUID): Course?





    // Insert a new class
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClass(classEntity: YogaClass)

    // Insert multiple classes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClasses(classes: List<YogaClass>)

    // Update an existing class
    @Update
    suspend fun updateClass(classEntity: YogaClass)

    // Delete a class
    @Delete
    suspend fun deleteClass(classEntity: YogaClass)

    // Query to get all classes
    @Query("SELECT * FROM YogaClasses")
    suspend fun getAllClasses(): List<YogaClass>

    // Query to get class by ID
    @Query("SELECT * FROM YogaClasses WHERE classId = :classId")
    suspend fun getClassById(classId: UUID): YogaClass?



    @Query("DELETE FROM ClassDates WHERE scheduleId = :scheduleId")
    suspend fun deleteClassDateByScheduleId(scheduleId: UUID): Int

    // Insert a new schedule
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: Schedule)

    // Insert multiple schedules
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedules(schedules: List<Schedule>)

    // Update an existing schedule
    @Update
    suspend fun updateSchedule(schedule: Schedule)

    // Delete a schedule
    @Delete
    suspend fun deleteSchedule(schedule: Schedule)

    // Query to get all schedules
    @Query("SELECT * FROM Schedules")
    suspend fun getAllSchedules(): List<Schedule>

    // Query to get schedule by ID
    @Query("SELECT * FROM Schedules WHERE scheduleId = :scheduleId")
    suspend fun getScheduleById(scheduleId: UUID): Schedule?




    // Insert a new instructor
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInstructor(instructor: Instructor)

    // Insert multiple instructors
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInstructors(instructors: List<Instructor>)

    // Update an existing instructor
    @Update
    suspend fun updateInstructor(instructor: Instructor)

    // Delete an instructor
    @Delete
    suspend fun deleteInstructor(instructor: Instructor)

    // Query to get all instructors
    @Query("SELECT * FROM Instructors")
    suspend fun getAllInstructors(): List<Instructor>

    // Query to get instructor by ID
    @Query("SELECT * FROM Instructors WHERE instructorId = :instructorId")
    suspend fun getInstructorById(instructorId: UUID): Instructor?












    // Query to retrieve all schedules by courseId
    @Query("SELECT * FROM Schedules WHERE courseId = :courseId")
    fun getSchedulesByCourseId(courseId: UUID): List<Schedule>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassDate(classDate: ClassDate)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassDates(classDate: List<ClassDate>)


    // Get all class dates for a specific schedule
    @Query("SELECT * FROM ClassDates WHERE scheduleId = :scheduleId")
    suspend fun getClassDatesBySchedule(scheduleId: UUID): List<ClassDate>


    // Get all class dates for a specific schedule
    @Query("SELECT * FROM ClassDates WHERE classDateId = :classDateId")
    suspend fun getClassDatesById(classDateId: UUID): ClassDate


    @Update
    suspend fun updateClassDate(classDate: ClassDate)

    // Delete a class date
    @Delete
    suspend fun deleteClassDate(classDate: ClassDate)



}
