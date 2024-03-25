package com.ruralnative.handsy.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.entities.AlphabetLesson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
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
class TestAlphabetLessonDao {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase

    private lateinit var dao: AlphabetLessonDao

    /**
     * Sets up the test environment before each test.
     *
     * This method injects the test instance of [AppDatabase] and initializes the [UserDao].
     */
    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.alphabetLessonDao()
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
        val testLesson = AlphabetLesson(
            id = 1,
            lessonName = "TestLesson",
            lessonDescription = "Test Description",
            lessonMediaFile = "test_media_file"
        )
        dao.insertLesson(testLesson)
        val retrievedLesson = dao.selectLessonById(testLesson.id).first()
        assertThat(retrievedLesson.lessonName, equalTo(testLesson.lessonName))
    }

    @Test
    fun updateLessonName() = runTest {
        val testLesson = AlphabetLesson(
            id = 1,
            lessonName = "OldName",
            lessonDescription = "Test Description",
            lessonMediaFile = "test_media_file"
        )
        dao.insertLesson(testLesson)
        dao.updateLessonName("NewName", testLesson.id)
        val updatedLesson = dao.selectLessonById(testLesson.id).first()
        assertThat(updatedLesson.lessonName, equalTo("NewName"))
    }

    @Test
    fun updateLessonDescription() = runTest {
        val testLesson = AlphabetLesson(
            id = 1,
            lessonName = "TestLesson",
            lessonDescription = "Old Description",
            lessonMediaFile = "test_media_file"
        )
        dao.insertLesson(testLesson)
        dao.updateLessonDescription("New Description", testLesson.id)
        val updatedLesson = dao.selectLessonById(testLesson.id).first()
        assertThat(updatedLesson.lessonDescription, equalTo("New Description"))
    }

    @Test
    fun updateLessonMediaFile() = runTest {
        val testLesson = AlphabetLesson(
            id = 1,
            lessonName = "TestLesson",
            lessonDescription = "Test Description",
            lessonMediaFile = "old_media_file"
        )
        dao.insertLesson(testLesson)
        dao.updateLessonMediaFile("new_media_file", testLesson.id)
        val updatedLesson = dao.selectLessonById(testLesson.id).first()
        assertThat(updatedLesson.lessonMediaFile, equalTo("new_media_file"))
    }

    @Test
    fun deleteLesson() = runTest {
        val testLesson = AlphabetLesson(
            id = 1,
            lessonName = "TestLesson",
            lessonDescription = "Test Description",
            lessonMediaFile = "test_media_file"
        )
        dao.insertLesson(testLesson)
        dao.deleteLesson(testLesson)
        val lessons = dao.selectAllLessons().first()
        assertThat(lessons, hasSize(0))
    }
}