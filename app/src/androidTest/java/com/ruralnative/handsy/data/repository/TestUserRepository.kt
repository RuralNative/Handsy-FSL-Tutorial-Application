package com.ruralnative.handsy.data.repository

import androidx.test.filters.SmallTest
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.di.TestDatabase
import com.ruralnative.handsy.data.di.TestUserRepo
import com.ruralnative.handsy.data.entities.User
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@SmallTest
class TestUserRepository {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    @TestDatabase lateinit var database: AppDatabase

    @Inject
    @TestUserRepo lateinit var repository: UserRepository

    @Before
    fun initDb() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndFetchUser() = runTest {
        println("TESTING: insertUser() AND getUserByID()")
        val user = User(
            1,
            "User One",
            1,
            1
        )
        repository.insertUser(user)
        val fetchedUser = repository.getUserByID(1)
        assertEquals(user, fetchedUser.first())
    }

    @Test
    fun checkExistenceOfUser() = runTest {
        println("TESTING: isThereAUser() AND deleteUser()")
        val user = User(
            1,
            "User One",
            1,
            1
        )
        repository.insertUser(user)
        println("TESTING: Log //Test of User Successfully ADDED?")
        assertEquals(user, repository.getUserByID(1).first())
        println("TESTING: Log //Check User Should Exist?")
        assertEquals(true, repository.isThereNoUser().first())
        repository.deleteUser(user)
        assertEquals(true, repository.isThereNoUser().first())
    }

    @Test
    fun updateUser() = runTest {
        println("TESTING: insertNewUser() AND updateUser()")
        repository.insertNewUser(1, "New User")
        val updatedUser = User(
            1,
            "Updated User",
            1,
            1
        )
        repository.updateUser(updatedUser)
        val fetchedUser = repository.getUserByID(1).first()
        assertEquals(updatedUser, fetchedUser)
    }

    @Test
    fun updateUserInformation() = runTest {
        println("TESTING: Update User Methods")
        repository.insertNewUser(1, "New User")
        repository.updateUserNameWithID("Updated User", 1)
        repository.updateUserStatusWithID(0, 1)
        repository.updateUserLevelWithID(2, 1)
        val comparedUser = User(
            1,
            "Updated User",
            0,
            2
        )
        val fetchedUpdatedUser = repository.getUserByID(1).first()
        assertEquals(comparedUser, fetchedUpdatedUser)
    }
}