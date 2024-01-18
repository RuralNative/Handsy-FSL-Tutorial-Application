package com.ruralnative.handsy.data.repository

import androidx.test.filters.SmallTest
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.di.TestAlphabetRepo
import com.ruralnative.handsy.di.TestDatabase
import com.ruralnative.handsy.data.entities.AlphabetLesson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@SmallTest
class TestAlphabetRepository {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @TestDatabase
    lateinit var database: AppDatabase

    @Inject
    @TestAlphabetRepo
    lateinit var repository: AlphabetLessonRepository

    @Before
    fun initDb() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetLesson() = runTest {
        val lesson = AlphabetLesson(
            1,
            "Lesson 1",
            "Lesson Desc 1",
            "Lesson Media File 1"
        )
        repository.insertLesson(lesson)
        val fetchedLesson = repository.getLessonByID(1).first()
        assertEquals(lesson, fetchedLesson)
    }

    @Test
    fun updateLesson() = runTest {
        val lesson = AlphabetLesson(
            1,
            "Lesson 1",
            "Lesson Desc 1",
            "Lesson Media File 1"
        )
        repository.insertLesson(lesson)
        val updatedLesson = AlphabetLesson(
            1,
            "Lesson 2",
            "Lesson Desc 2",
            "Lesson Media File 2"
        )
        repository.updateLesson(updatedLesson)
        val fetchedLesson = repository.getLessonByID(1).first()
        assertEquals(updatedLesson, fetchedLesson)
    }

    @Test
    fun updateLessonInfo() = runTest {
        val lesson = AlphabetLesson(
            1,
            "Lesson 1",
            "Lesson Desc 1",
            "Lesson Media File 1"
        )
        repository.insertLesson(lesson)
        repository.updateLessonName("Lesson 2", 1)
        repository.updateLessonDescription("Lesson Desc 2", 1)
        repository.updateLessonMediaFile("Lesson Media File 2", 1)
        val comparedLesson = AlphabetLesson(
            1,
            "Lesson 2",
            "Lesson Desc 2",
            "Lesson Media File 2"
        )
        val updatedLesson = repository.getLessonByID(1).first()
        assertEquals(comparedLesson, updatedLesson)
    }


}