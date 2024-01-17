package com.ruralnative.handsy.data.dao

import androidx.test.filters.SmallTest
import com.ruralnative.handsy.data.AppDatabase
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
    @Named("test_database")
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
}