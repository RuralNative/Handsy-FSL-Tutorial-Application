package com.ruralnative.handsy.data.dao

import androidx.test.filters.SmallTest
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.di.TestDatabase
import com.ruralnative.handsy.data.entities.AlphabetLesson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class TestAlphabetDAO {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @TestDatabase
    lateinit var database: AppDatabase
    private lateinit var dao: AlphabetLessonDao

    @Before
    fun initDb() {
        hiltRule.inject()
        dao = database.alphabetLessonDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun selectAllLessons() = runTest {
        println("TESTING: selectAllLessons()")
        for (i in 1..10) {
            val lesson = AlphabetLesson(
                i,
                "Lesson $i",
                "Lesson Desc $i",
                "Lesson Media File $i"
            )
            dao.insertLesson(lesson)
        }
        val allLessonsList = dao.selectAllLessons().first()
        assertEquals(10, allLessonsList.size)
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