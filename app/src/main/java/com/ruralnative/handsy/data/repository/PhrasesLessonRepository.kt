package com.ruralnative.handsy.data.repository

import androidx.annotation.WorkerThread
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.entities.PhrasesLesson
import kotlinx.coroutines.flow.Flow


@Suppress("RedundantSuspendModifier")
@WorkerThread
class PhrasesLessonRepository(private val dao: PhrasesLessonDao) {
    val allLessons: Flow<List<PhrasesLesson>> = dao.selectAllLessons()

    suspend fun getLessonByID(lessonID: Int): Flow<PhrasesLesson> {
        return dao.selectLessonById(lessonID)
    }

    suspend fun insertLesson(lesson: PhrasesLesson) {
        dao.insertLesson(lesson)
    }

    suspend fun updateLesson(lesson: PhrasesLesson) {
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