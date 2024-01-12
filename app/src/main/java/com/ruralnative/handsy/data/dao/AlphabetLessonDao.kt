package com.ruralnative.handsy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ruralnative.handsy.data.entities.AlphabetLesson
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow

@Dao
@InstallIn(SingletonComponent::class)
interface AlphabetLessonDao {

    @Query("SELECT * from alphabet_lessons")
    fun selectAllLessons(): Flow<List<AlphabetLesson>>

    @Query("SELECT * from alphabet_lessons WHERE id = :lessonID")
    suspend fun selectLessonById(lessonID: Int): Flow<AlphabetLesson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: AlphabetLesson)

    @Update
    suspend fun updateLesson(lesson: AlphabetLesson)

    @Query("UPDATE alphabet_lessons SET lesson_name = :lessonName WHERE id = :lessonID")
    suspend fun updateLessonName(lessonName: String?, lessonID: Int)

    @Query("UPDATE alphabet_lessons SET lesson_description = :lessonDescription WHERE id = :lessonID")
    suspend fun updateLessonDescription(lessonDescription: String?, lessonID: Int)

    @Query("UPDATE alphabet_lessons SET lesson_media_file = :lessonMediaFile WHERE id = :lessonID")
    suspend fun updateLessonMediaFile(lessonMediaFile: String?, lessonID: Int)

    @Delete
    suspend fun deleteLesson(lesson: AlphabetLesson)
}