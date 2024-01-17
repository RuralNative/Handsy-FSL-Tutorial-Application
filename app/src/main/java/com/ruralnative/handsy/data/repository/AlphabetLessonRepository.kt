package com.ruralnative.handsy.data.repository

import androidx.annotation.WorkerThread
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.di.AlphabetDAO
import com.ruralnative.handsy.di.UserDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@WorkerThread
class AlphabetLessonRepository @Inject constructor(
    @AlphabetDAO private val dao: AlphabetLessonDao
) {

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