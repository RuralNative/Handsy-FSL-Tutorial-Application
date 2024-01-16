package com.ruralnative.handsy.data.dao

import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.entities.AlphabetLesson
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

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
        //Test Before Delete
        var numberOfUsersUndeleted = 0
        val insertLessonJob : Job = launch {
            dao.insertLesson(lessonOne)
            dao.insertLesson(lessonTwo)
            delay(1000L)
            val usersListFlow = dao.selectAllLessons()
            usersListFlow.collectLatest { users ->
                numberOfUsersUndeleted = users.size
            }
        }
        insertLessonJob.join()
        assertEquals(2, numberOfUsersUndeleted)
        //Test After Delete
        var numberOfUsersAfterDelete = 0
        val deleteLessonJob : Job = launch {
            dao.deleteLesson(lessonOne)
            delay(1000L)
            val usersListFlow = dao.selectAllLessons()
            usersListFlow.collectLatest { users ->
                numberOfUsersAfterDelete = users.size
            }
        }
        deleteLessonJob.join()
        assertEquals(1, numberOfUsersAfterDelete)
    }
}