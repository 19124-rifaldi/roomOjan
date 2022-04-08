package com.binar.challange22.room

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.binar.challange22.Student


@Dao
interface StudentDao {
    @Query("SELECT * FROM Student")
    fun getAllStudent(): List<Student>

    @Insert(onConflict = REPLACE)
    fun insertStudent(student: Student):Long

    @Update
    fun updateStudent(student: Student):Int

    @Delete
    fun deleteStudent(student: Student):Int
}