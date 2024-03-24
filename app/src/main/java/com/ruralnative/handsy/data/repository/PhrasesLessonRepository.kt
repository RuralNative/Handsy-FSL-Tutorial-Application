package com.ruralnative.handsy.data.repository

import androidx.annotation.WorkerThread
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.entities.PhrasesLesson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for interacting with phrases lessons in the database.
 *
 * This class provides methods to perform CRUD operations on phrases lessons.
 * It uses a [PhrasesLessonDao] to interact with the database.
 *
 * @property dao The Data Access Object for accessing phrases lesson data.
 * @constructor Creates a new instance of PhrasesLessonRepository.
 */
@Singleton
@WorkerThread
@JvmSuppressWildcards
class PhrasesLessonRepository @Inject constructor(
    private val dao: PhrasesLessonDao
) {

    /**
     * Flow of all phrases lessons in the database.
     */
    val allLessons: Flow<List<PhrasesLesson>> = dao.selectAllLessons()

    /**
     * Returns a flow of the phrases lesson with the given ID.
     *
     * @param lessonID The ID of the phrases lesson.
     * @return A flow of the phrases lesson.
     */
    fun getLessonByID(lessonID: Int): Flow<PhrasesLesson> {
        return dao.selectLessonById(lessonID)
    }

    /**
     * Updates the media file of a phrases lesson in the database.
     *
     * @param lessonMediaFile The new media file for the phrases lesson.
     * @param lessonID The ID of the phrases lesson to update.
     */
    suspend fun updateLessonMediaFile(lessonMediaFile: String?, lessonID: Int) {
        dao.updateLessonMediaFile(lessonMediaFile, lessonID)
    }
}