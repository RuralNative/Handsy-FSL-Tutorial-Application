package com.ruralnative.handsy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.data.entities.PhrasesLesson
import kotlinx.coroutines.flow.Flow

@Dao
interface PhrasesLessonDao {

    @Query("SELECT * from phrases_lessons")
    fun selectAllLessons(): Flow<List<PhrasesLesson>>

    @Query("SELECT * from phrases_lessons WHERE id = :lessonID")
    fun selectLessonById(lessonID: Int): Flow<PhrasesLesson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLesson(lesson: PhrasesLesson)

    @Update
    fun updateLesson(lesson: PhrasesLesson)

    @Query("UPDATE phrases_lessons SET lesson_name = :lessonName WHERE id = :lessonID")
    fun updateLessonName(lessonName: String?, lessonID: Int)

    @Query("UPDATE phrases_lessons SET lesson_description = :lessonDescription WHERE id = :lessonID")
    fun updateLessonDescription(lessonDescription: String?, lessonID: Int)

    @Query("UPDATE phrases_lessons SET lesson_media_file = :lessonMediaFile WHERE id = :lessonID")
    fun updateLessonMediaFile(lessonMediaFile: String?, lessonID: Int)

    @Delete
    fun deleteLesson(lesson: PhrasesLesson)
}