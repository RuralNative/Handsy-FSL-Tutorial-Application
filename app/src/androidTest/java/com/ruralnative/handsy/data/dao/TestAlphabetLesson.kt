package com.ruralnative.handsy.data.dao

import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.entities.AlphabetLesson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class TestAlphabetLesson {

    private lateinit var database: AppDatabase
    private lateinit var dao: AlphabetLessonDao

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java
        ).build()
        dao = database.alphabetLessonDao()
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteAndCheckDatabaseContent() = runTest {
        println("TESTING: deleteLesson(), selectAllLessons()")

        val lessonOne = AlphabetLesson(
            1,
            "Lesson 1",
            "Lesson Desc 1",
            "Lesson Media 1"
        )
        val lessonTwo = AlphabetLesson(
            2,
            "Lesson 2",
            "Lesson Desc 2",
            "Lesson Media 2"
        )

        var numberOfUsersUndeleted = 0

        dao.insertLesson(lessonOne)
        dao.insertLesson(lessonTwo)
        val flowListOfUndeleted = dao.selectAllLessons()
        flowListOfUndeleted.collect { users ->
            numberOfUsersUndeleted = users.size
        }
        assertEquals(2, numberOfUsersUndeleted)
    }
}