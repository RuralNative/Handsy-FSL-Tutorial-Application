package com.ruralnative.handsy.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.entities.User
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.chromium.base.Callback
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class TestUserDao {

    @get:Rule
    var hiltRule = HiltAndroidRule(this) {
        // Callback to be notified when injection is complete
        callback = object : HiltAndroidRule.Callback {
            override fun onProvideAndroidEnvironmentAfterInjection() {
                initializationLatch.countDown()
            }
        }
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val initializationLatch = CountDownLatch(1)

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase

    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        hiltRule.inject()
        initializationLatch.await()
        userDao = database.userDao()
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
            isNewUser = 1,
            progressionLevel = 1
        )
        userDao.insertUser(testUser)
        val retrievedUser = userDao.selectUserById(testUser.id).first()
        assertThat(retrievedUser.userName, equalTo(testUser.userName))
    }

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