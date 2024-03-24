package com.ruralnative.handsy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.data.entities.PhrasesLesson
import com.ruralnative.handsy.data.entities.User
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [PhrasesLesson] class as an Interface.
 *
 * This interface defines methods for interacting with the `phrases_lessons` table in the database.
 * It includes methods for querying, inserting, updating, and deleting [PhrasesLesson] objects.
 *
 * @constructor Creates a new instance of PhrasesLessonDao.
 */
@Dao
interface PhrasesLessonDao {

    /**
     * Returns a Flow of List of all [PhrasesLesson] in the database.
     *
     * @return A Flow of List of all phrases lessons.
     */
    @Query("SELECT * from phrases_lessons")
    fun selectAllLessons(): Flow<List<PhrasesLesson>>

    /**
     * Returns a Flow of [PhrasesLesson] with the given id.
     *
     * @param lessonID the id of the [PhrasesLesson] to return.
     * @return A Flow of the phrases lesson with the specified ID.
     */
    @Query("SELECT * from phrases_lessons WHERE id = :lessonID")
    fun selectLessonById(lessonID: Int): Flow<PhrasesLesson>

    /**
     * Inserts a [PhrasesLesson] into the database.
     *
     * @param lesson the [PhrasesLesson] to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: PhrasesLesson)

    /**
     * Updates a [PhrasesLesson] in the database.
     *
     * @param lesson the [PhrasesLesson] to update.
     */
    @Update
    suspend fun updateLesson(lesson: PhrasesLesson)

    /**
     * Updates the name of a [PhrasesLesson] in the database.
     *
     * @param lessonName the new name of the [PhrasesLesson].
     * @param lessonID the id of the [PhrasesLesson] to update.
     */
    @Query("UPDATE phrases_lessons SET lesson_name = :lessonName WHERE id = :lessonID")
    suspend fun updateLessonName(lessonName: String?, lessonID: Int)

    /**
     * Updates the description of a [PhrasesLesson] in the database.
     *
     * @param lessonDescription the new description of the [PhrasesLesson].
     * @param lessonID the id of the [PhrasesLesson] to update.
     */
    @Query("UPDATE phrases_lessons SET lesson_description = :lessonDescription WHERE id = :lessonID")
    suspend fun updateLessonDescription(lessonDescription: String?, lessonID: Int)

    /**
     * Updates the media file of a [PhrasesLesson] in the database.
     *
     * @param lessonMediaFile the new media file of the [PhrasesLesson].
     * @param lessonID the id of the [PhrasesLesson] to update.
     */
    @Query("UPDATE phrases_lessons SET lesson_media_file = :lessonMediaFile WHERE id = :lessonID")
    suspend fun updateLessonMediaFile(lessonMediaFile: String?, lessonID: Int)

    /**
     * Deletes a [PhrasesLesson] from the database.
     *
     * @param lesson the [PhrasesLesson] to delete.
     */
    @Delete
    suspend fun deleteLesson(lesson: PhrasesLesson)
}
