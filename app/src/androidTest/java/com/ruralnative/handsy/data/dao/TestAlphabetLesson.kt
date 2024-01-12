package com.ruralnative.handsy.data.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.entities.AlphabetLesson
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class TestAlphabetLesson {

    @Inject
    lateinit var database: AppDatabase
    @Inject
    lateinit var dao: AlphabetLessonDao

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
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

}