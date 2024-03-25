package com.ruralnative.handsy.data.dao

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.entities.User
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
class TestUserDao {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase

    private lateinit var userDao: UserDao

    /**
     * Sets up the test environment before each test.
     *
     * This method injects the test instance of [AppDatabase] and initializes the [UserDao].
     */
    @Before
    fun setup() {
        hiltRule.inject()
        userDao = database.userDao()
    }

    /**
     * Closes the database after each test.
     */
    @After
    fun tearDown() {
        database.close()
    }

    /**
     * Tests the insertion and retrieval of a user.
     *
     * This test verifies that a user can be inserted into the database and then retrieved
     * by its ID, ensuring that the insertion operation was successful.
     */
    @Test
    fun insertAndRetrieveUser() = runTest {
        Log.d("TestUserDao", "insertAndRetrieveUser INIT")
        val testUser = User(
            id = 1,
            userName = "TestUser",
            isNewUser = 1,
            progressionLevel = 1
        )
        userDao.insertUser(testUser)
        val retrievedUser = userDao.selectUserById(testUser.id).first()
        assertThat(retrievedUser.userName, equalTo(testUser.userName))
        Log.d("TestUserDao", "insertAndRetrieveUser FINISHED")
    }

    /**
     * Tests the update of a user's name.
     *
     * This test verifies that the name of a user can be updated in the database.
     */
    @Test
    fun updateUserName() = runTest {
        val testUser = User(
            id = 1,
            userName = "OldName",
            isNewUser = 1,
            progressionLevel = 1
        )
        userDao.insertUser(testUser)
        userDao.updateUserName("NewName", testUser.id)
        val updatedUser = userDao.selectUserById(testUser.id).first()
        assertThat(updatedUser.userName, equalTo("NewName"))
    }

    /**
     * Tests the update of a user's status.
     *
     * This test verifies that the status of a user can be updated in the database.
     */
    @Test
    fun updateUserStatus() = runTest {
        val testUser = User(
            id = 1,
            userName = "TestUser",
            isNewUser = 1,
            progressionLevel = 1
        )
        userDao.insertUser(testUser)
        userDao.updateUserStatus(0, testUser.id)
        val updatedUser = userDao.selectUserById(testUser.id).first()
        assertThat(updatedUser.isNewUser, equalTo(0))
    }

    /**
     * Tests the update of a user's progression level.
     *
     * This test verifies that the progression level of a user can be updated in the database.
     */
    @Test
    fun updateUserProgressionLevel() = runTest {
        val testUser = User(
            id = 1,
            userName = "TestUser",
            isNewUser = 1,
            progressionLevel = 1
        )
        userDao.insertUser(testUser)
        userDao.updateUserProgressionLevel(2, testUser.id)
        val updatedUser = userDao.selectUserById(testUser.id).first()
        assertThat(updatedUser.progressionLevel, equalTo(2))
    }

    /**
     * Tests the deletion of a user.
     *
     * This test verifies that a user can be deleted from the database.
     */
    @Test
    fun deleteUser() = runTest {
        val testUser = User(
            id = 1,
            userName = "TestUser",
            isNewUser = 1,
            progressionLevel = 1
        )
        userDao.insertUser(testUser)
        userDao.deleteUser(testUser)
        val users = userDao.selectAllUsers().first()
        assertThat(users, hasSize(0))
    }
}