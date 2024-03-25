package com.ruralnative.handsy.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.dao.UserDao
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
class TestUserRepository {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase

    private lateinit var userDao: UserDao
    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        hiltRule.inject()
        userDao = database.userDao()
        userRepository = UserRepository(userDao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndRetrieveUser() = runTest {
        val testUser = User(
            id = 1,
            userName = "TestUser",
            isNewUser = 0,
            progressionLevel = 1
        )
        userRepository.insertUser(testUser)
        val retrievedUser = userRepository.getUserByID(testUser.id).first()
        assertThat(retrievedUser.userName, equalTo(testUser.userName))
    }

    @Test
    fun updateUserName() = runTest {
        val testUser = User(
            id = 1,
            userName = "OldName",
            isNewUser = 0,
            progressionLevel = 1
        )
        userRepository.insertUser(testUser)
        userRepository.updateUserNameWithID("NewName", testUser.id)
        val updatedUser = userRepository.getUserByID(testUser.id).first()
        assertThat(updatedUser.userName, equalTo("NewName"))
    }

    @Test
    fun updateUserStatus() = runTest {
        val testUser = User(
            id = 1,
            userName = "TestUser",
            isNewUser = 0,
            progressionLevel = 1
        )
        userRepository.insertUser(testUser)
        userRepository.updateUserStatusWithID(1, testUser.id)
        val updatedUser = userRepository.getUserByID(testUser.id).first()
        assertThat(updatedUser.isNewUser, equalTo(1))
    }

    @Test
    fun updateUserLevel() = runTest {
        val testUser = User(
            id = 1,
            userName = "TestUser",
            isNewUser = 0,
            progressionLevel = 1
        )
        userRepository.insertUser(testUser)
        userRepository.updateUserLevelWithID(2, testUser.id)
        val updatedUser = userRepository.getUserByID(testUser.id).first()
        assertThat(updatedUser.progressionLevel, equalTo(2))
    }

    @Test
    fun deleteUser() = runTest {
        val testUser = User(
            id = 1,
            userName = "TestUser",
            isNewUser = 0,
            progressionLevel = 1
        )
        userRepository.insertUser(testUser)
        userRepository.deleteUser(testUser)
        val users = userRepository.allUsers.first()
        assertThat(users, hasSize(0))
    }
}