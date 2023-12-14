package com.ruralnative.handsy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ruralnative.handsy.data.entities.AlphabetLesson

@Dao
interface PhrasesLessonDao {

    @Query("SELECT * from alphabet_lessons WHERE id = :lessonID")
    fun selectLessonById(lessonID: Int): AlphabetLesson

    @Query("SELECT * from alphabet_lessons")
    fun selectAllLessons(): List<AlphabetLesson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLesson(lesson: PhrasesLessonDao)

    @Update
    fun updateLesson(lesson: PhrasesLessonDao)

    @Query("UPDATE alphabet_lessons SET lesson_name = :lessonName WHERE id = :lessonID")
    fun updateLessonName(lessonName: String?, lessonID: String)

    @Query("UPDATE alphabet_lessons SET lesson_description = :lessonDescription WHERE id = :lessonID")
    fun updateLessonDescription(lessonDescription: String?, lessonID: String)

    @Query("UPDATE alphabet_lessons SET lesson_media_file = :lessonMediaFile WHERE id = :lessonID")
    fun updateLessonMediaFile(lessonMediaFile: String?, lessonID: String)

    @Delete
    fun deleteLesson(lesson: PhrasesLessonDao)
}