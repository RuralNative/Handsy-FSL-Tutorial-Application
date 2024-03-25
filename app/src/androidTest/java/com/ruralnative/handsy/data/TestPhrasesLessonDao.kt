package com.ruralnative.handsy.data

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.entities.PhrasesLesson
import com.ruralnative.handsy.data.entities.User
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/**
 * Test class for verifying the functionality of [UserDao] operations.
 *
 * This class uses Hilt for dependency injection to provide a test instance of [AppDatabase]
 * and tests the operations of [UserDao] such as insertion, retrieval, updating, and deletion of users.
 *
 * @constructor Creates a new instance of TestUserDao.
 */
@HiltAndroidTest
@SmallTest
class TestPhrasesLessonDao {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase

    private lateinit var phrasesLessonDao: PhrasesLessonDao

    /**
     * Sets up the test environment before each test.
     *
     * This method injects the test instance of [AppDatabase] and initializes the [UserDao].
     */
    @Before
    fun setup() {
        hiltRule.inject()
        phrasesLessonDao = database.phrasesLessonDao()
    }

    /**
     * Closes the database after each test.
     */
    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndRetrieveLesson() = runTest {
        val testLesson = PhrasesLesson(
            id = 1,
            lessonName = "TestLesson",
            lessonDescription = "Test Description",
            lessonMediaFile = "test_media_file"
        )
        phrasesLessonDao.insertLesson(testLesson)
        val retrievedLesson = phrasesLessonDao.selectLessonById(testLesson.id).first()
        assertThat(retrievedLesson.lessonName, equalTo(testLesson.lessonName))
    }

    @Test
    fun updateLessonName() = runTest {
        val testLesson = PhrasesLesson(
            id = 1,
            lessonName = "OldName",
            lessonDescription = "Test Description",
            lessonMediaFile = "test_media_file"
        )
        phrasesLessonDao.insertLesson(testLesson)
        phrasesLessonDao.updateLessonName("NewName", testLesson.id)
        val updatedLesson = phrasesLessonDao.selectLessonById(testLesson.id).first()
        assertThat(updatedLesson.lessonName, equalTo("NewName"))
    }

    @Test
    fun updateLessonDescription() = runTest {
        val testLesson = PhrasesLesson(
            id = 1,
            lessonName = "TestLesson",
            lessonDescription = "Old Description",
            lessonMediaFile = "test_media_file"
        )
        phrasesLessonDao.insertLesson(testLesson)
        phrasesLessonDao.updateLessonDescription("New Description", testLesson.id)
        val updatedLesson = phrasesLessonDao.selectLessonById(testLesson.id).first()
        assertThat(updatedLesson.lessonDescription, equalTo("New Description"))
    }

    @Test
    fun updateLessonMediaFile() = runTest {
        val testLesson = PhrasesLesson(
            id = 1,
            lessonName = "TestLesson",
            lessonDescription = "Test Description",
            lessonMediaFile = "old_media_file"
        )
        phrasesLessonDao.insertLesson(testLesson)
        phrasesLessonDao.updateLessonMediaFile("new_media_file", testLesson.id)
        val updatedLesson = phrasesLessonDao.selectLessonById(testLesson.id).first()
        assertThat(updatedLesson.lessonMediaFile, equalTo("new_media_file"))
    }

    @Test
    fun deleteLesson() = runTest {
        val testLesson = PhrasesLesson(
            id = 1,
            lessonName = "TestLesson",
            lessonDescription = "Test Description",
            lessonMediaFile = "test_media_file"
        )
        phrasesLessonDao.insertLesson(testLesson)
        phrasesLessonDao.deleteLesson(testLesson)
        val lessons = phrasesLessonDao.selectAllLessons().first()
        assertThat(lessons, hasSize(0))
    }
}