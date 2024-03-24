package com.ruralnative.handsy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.data.entities.User
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [AlphabetLesson] class as an Interface.
 */
@Dao
interface AlphabetLessonDao {

    /**
     * Returns a Flow of List of all [AlphabetLesson] in the database.
     */
    @Query("SELECT * from alphabet_lessons")
    fun selectAllLessons(): Flow<List<AlphabetLesson>>

    /**
     * Returns a Flow of [AlphabetLesson] with the given id.
     * @param lessonID the id of the [AlphabetLesson]
     */
    @Query("SELECT * from alphabet_lessons WHERE id = :lessonID")
    fun selectLessonById(lessonID: Int): Flow<AlphabetLesson>

    /**
     * Inserts a [AlphabetLesson] in the database.
     * @param lesson the [AlphabetLesson]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: AlphabetLesson)

    /**
     * Updates a [AlphabetLesson] in the database.
     * @param lesson the [AlphabetLesson]
     */
    @Update
    suspend fun updateLesson(lesson: AlphabetLesson)

    /**
     * Updates the name of a [AlphabetLesson] with a given ID
     * @param lessonName the new name of the [AlphabetLesson]
     * @param lessonID the id of the [AlphabetLesson]
     */
    @Query("UPDATE alphabet_lessons SET lesson_name = :lessonName WHERE id = :lessonID")
    suspend fun updateLessonName(lessonName: String?, lessonID: Int)

    /**
     * Updates the description of a [AlphabetLesson] with a given ID
     * @param lessonDescription the new description of the [AlphabetLesson]
     * @param lessonID the id of the [AlphabetLesson]
     */
    @Query("UPDATE alphabet_lessons SET lesson_description = :lessonDescription WHERE id = :lessonID")
    suspend fun updateLessonDescription(lessonDescription: String?, lessonID: Int)

    /**
     * Updates the media file of a [AlphabetLesson] with a given ID
     * @param lessonMediaFile the new media file of the [AlphabetLesson]
     * @param lessonID the id of the [AlphabetLesson]
     */
    @Query("UPDATE alphabet_lessons SET lesson_media_file = :lessonMediaFile WHERE id = :lessonID")
    suspend fun updateLessonMediaFile(lessonMediaFile: String?, lessonID: Int)

    /**
     * Deletes a [AlphabetLesson] from the database.
     * @param lesson the [AlphabetLesson]
     */
    @Delete
    suspend fun deleteLesson(lesson: AlphabetLesson)
}