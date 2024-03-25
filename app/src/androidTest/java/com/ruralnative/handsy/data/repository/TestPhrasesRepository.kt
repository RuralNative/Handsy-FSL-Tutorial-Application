package com.ruralnative.handsy.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.data.entities.PhrasesLesson
import com.ruralnative.handsy.data.entities.User
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class TestPhrasesRepository {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase

    private lateinit var phrasesLessonDao: PhrasesLessonDao
    private lateinit var phrasesLessonRepository: PhrasesLessonRepository

    @Before
    fun setup() {
        hiltRule.inject()
        phrasesLessonDao = database.phrasesLessonDao()
        phrasesLessonRepository = PhrasesLessonRepository(phrasesLessonDao)
    }

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
        val retrievedLesson = phrasesLessonRepository.getLessonByID(testLesson.id).first()
        assertThat(retrievedLesson.lessonName, equalTo(testLesson.lessonName))
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
        phrasesLessonRepository.updateLessonMediaFile("new_media_file", testLesson.id)
        val updatedLesson = phrasesLessonRepository.getLessonByID(testLesson.id).first()
        assertThat(updatedLesson.lessonMediaFile, equalTo("new_media_file"))
    }
}