package com.ruralnative.handsy.data.repository

import androidx.annotation.WorkerThread
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.entities.AlphabetLesson
import kotlinx.coroutines.flow.Flow


@Suppress("RedundantSuspendModifier")
@WorkerThread
class AlphabetLessonRepository(private val dao: AlphabetLessonDao) {
    val allLessons: Flow<List<AlphabetLesson>> = dao.selectAllLessons()

    suspend fun getLessonByID(lessonID: Int): Flow<AlphabetLesson> {
        return dao.selectLessonById(lessonID)
    }

    suspend fun insertLesson(lesson: AlphabetLesson) {
        dao.insertLesson(lesson)
    }

    suspend fun updateLesson(lesson: AlphabetLesson) {
        dao.updateLesson(lesson)
    }

    suspend fun updateLessonName(lessonName: String?, lessonID: Int) {
        dao.updateLessonName(lessonName, lessonID)
    }

    suspend fun updateLessonDescription(lessonDescription: String?, lessonID: Int) {
        dao.updateLessonDescription(lessonDescription, lessonID)
    }

    suspend fun updateLessonMediaFile(lessonMediaFile: String?, lessonID: Int) {
        dao.updateLessonMediaFile(lessonMediaFile, lessonID)
    }


}