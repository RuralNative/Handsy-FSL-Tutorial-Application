package com.ruralnative.handsy.data.dao

import androidx.test.filters.SmallTest
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.di.TestDatabase
import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.data.entities.PhrasesLesson
import com.ruralnative.handsy.data.entities.User
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
class TestUserDAO {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @TestDatabase
    lateinit var database: AppDatabase
    private lateinit var dao: UserDao

    @Before
    fun initDb() {
        hiltRule.inject()
        dao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun retrieveAllUsers() = runTest {
        println("TESTING: insertUser(), electAllUsers(), AND countUsers()")
        for (i in 1..5) {
            val user = User(
                i,
                "User $i",
                i,
                i
            )
            dao.insertUser(user)
        }
        val usersCount: Int = dao.countUsers().first()
        assertEquals(5, usersCount)
    }

    @Test
    fun insertAndRetrieveUser() = runTest {
        println("TESTING: insertUser() and selectUserByID()")
        val user = User(
            1,
            "User One",
            1,
            1
        )
        dao.insertUser(user)
        val retrievedUser = dao.selectUserById(1).firstOrNull()
        assertEquals(user, retrievedUser)
    }

    @Test
    fun updateUser() = runTest {
        println("TESTING: insertUser(), selectUserByID() AND updateUser()")
        val user = User(
            1,
            "User 1",
            1,
            1
        )
        dao.insertUser(user)
        val updatedUser = User(
            1,
            "User 2",
            2,
            2
        )
        dao.updateUser(updatedUser)
        val fetchedUser = dao.selectUserById(1).first()
        assertEquals(updatedUser, fetchedUser)
    }

    @Test
    fun updateUserInfo() = runTest {
        println("TESTING: DAO Update Methods")
        val user = User(
            1,
            "User 1",
            1,
            1
        )
        dao.insertUser(user)
        val comparedUser = User(
            1,
            "User 2",
            2,
            2
        )
        dao.updateUserName("User 2", 1)
        dao.updateUserStatus(2, 1)
        dao.updateUserProgressionLevel(2, 1)
        val updatedUser = dao.selectUserById(1).first()
        assertEquals(comparedUser, updatedUser)
    }
}