package com.ruralnative.handsy.data.dao

import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.entities.AlphabetLesson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
class TestAlphabetLesson {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var dao: AlphabetLessonDao

    @Before
    fun initDb() {
        hiltRule.inject()
    }

    @Test
    fun insertAndRetrieveLesson() = runTest {
        println("TESTING: insertLesson() and selectLessonByID()")
        val lesson = AlphabetLesson(
            1,
            "Lesson 1",
            "Lesson Desc 1",
            "Lesson Media File 1"
        )
        dao.insertLesson(lesson)
        val retrievedLesson = dao.selectLessonById(lesson.id)
        assertEquals(lesson, retrievedLesson.firstOrNull())
        println("TESTED: insertLesson() and selectLessonByID()")
    }

    @Test
    fun updateLesson() = runTest {
        println("TESTING: updateLesson()")
        val lesson = AlphabetLesson(
            1,
            "Lesson 1",
            "Lesson Desc 1",
            "Lesson Media File 1"
        )
        dao.insertLesson(lesson)
        val updatedLesson = AlphabetLesson(
            1,
            "MOD Lesson 1",
            "MOD Lesson Desc 1",
            "MOD Lesson Media File 1"
        )
        dao.updateLesson(updatedLesson)
        val retrievedUpdatedLesson = dao.selectLessonById(1)
        assertEquals(retrievedUpdatedLesson.firstOrNull(), updatedLesson)
    }

    @Test
    fun updateLessonName() = runTest {
        println("TESTING: updateLessonName(), updateLessonDescription, updateLessonMediaFile")
        val lesson = AlphabetLesson(
            1,
            "Lesson 1",
            "Lesson Desc 1",
            "Lesson Media File 1"
        )
        dao.insertLesson(lesson)
        dao.updateLessonName("Lesson 2", 1)
        dao.updateLessonDescription("Lesson Desc 2", 1)
        dao.updateLessonMediaFile("Lesson Media File 2", 1)
        val comparedLesson = AlphabetLesson(
            1,
            "Lesson 2",
            "Lesson Desc 2",
            "Lesson Media File 2"
        )
        val updatedLesson = dao.selectLessonById(1).firstOrNull()
        assertEquals(updatedLesson, comparedLesson)
    }
}