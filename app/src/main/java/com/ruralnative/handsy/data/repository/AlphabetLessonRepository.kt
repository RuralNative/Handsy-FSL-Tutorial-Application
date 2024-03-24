package com.ruralnative.handsy.data.repository

import androidx.annotation.WorkerThread
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.entities.AlphabetLesson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for interacting with alphabet lessons in the database.
 *
 * This class provides methods to perform CRUD operations on alphabet lessons.
 * It uses a [AlphabetLessonDao] to interact with the database.
 *
 * @property dao The Data Access Object for accessing alphabet lesson data.
 * @constructor Creates a new instance of AlphabetLessonRepository.
 */
@Singleton
@WorkerThread
@JvmSuppressWildcards
class AlphabetLessonRepository @Inject constructor(
    private val dao: AlphabetLessonDao
) {

    /**
     * Flow of all alphabet lessons in the database.
     */
    val allLessons: Flow<List<AlphabetLesson>> = dao.selectAllLessons()

    /**
     * Returns a flow of the alphabet lesson with the given ID.
     *
     * @param lessonID The ID of the alphabet lesson.
     * @return A flow of the alphabet lesson.
     */
    fun getLessonByID(lessonID: Int): Flow<AlphabetLesson> {
        return dao.selectLessonById(lessonID)
    }

    /**
     * Updates the media file of an alphabet lesson in the database.
     *
     * @param lessonMediaFile The new media file for the alphabet lesson.
     * @param lessonID The ID of the alphabet lesson to update.
     */
    suspend fun updateLessonMediaFile(lessonMediaFile: String?, lessonID: Int) {
        dao.updateLessonMediaFile(lessonMediaFile, lessonID)
    }
}