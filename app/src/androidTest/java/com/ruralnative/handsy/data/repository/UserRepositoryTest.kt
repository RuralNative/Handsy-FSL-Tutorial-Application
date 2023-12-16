package com.ruralnative.handsy.data.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ruralnative.handsy.data.AppDatabase
import com.ruralnative.handsy.data.entities.User
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class UserRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userRepository = UserRepository(database.userDao())
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertUser() = runTest {
        val user = User(1, "John Berlin", 0, 1)
        userRepository.insertUser(user)
        val users = userRepository.allUsers.toList()
        assert(users.contains(user as User))
    }

    @Test
    fun getUserByID() = runTest {
        val user = User(1, "John Berlin", 0, 1)
        userRepository.insertUser(user)
        val retrievedUser = userRepository.getUserByID(1).toList()
        assertEquals(user, retrievedUser.first())
    }

    @Test
    fun updateUser() = runTest {
        val user = User(1, "John Berlin", 0, 1)
        userRepository.insertUser(user)
        val updatedUser = user.copy(userName = "Jackelyn Hernaez")
        userRepository.updateUser(updatedUser)
        val retrievedUser = userRepository.getUserByID(1).toList()
        assertEquals(updatedUser, retrievedUser.first())
    }

    @Test
    fun updateUserNameWithID() = runTest {
        val user = User(1, "John Berlin", 0, 1)
        userRepository.insertUser(user)
        userRepository.updateUserNameWithID("Jomer", 1)
        val updatedUser = user.copy(userName = "Jomer")
        val retrievedUser = userRepository.getUserByID(1).toList()
        assertEquals(updatedUser, retrievedUser.first())
    }

    @Test
    fun updateUserStatusWithID() = runTest {
        val user = User(1, "John Berlin", 0, 1)
        userRepository.insertUser(user)
        userRepository.updateUserStatusWithID(1, 1)
        val updatedUser = user.copy(isNewUser = 1)
        val retrievedUser = userRepository.getUserByID(1).toList()
        assertEquals(updatedUser, retrievedUser.first())
    }

    @Test
    fun updateUserLevelWithID() = runTest {
        val user = User(1, "John Berlin", 0, 1)
        userRepository.insertUser(user)
        userRepository.updateUserLevelWithID(2, 1)
        val updatedUser = user.copy(progressionLevel = 2)
        val retrievedUser = userRepository.getUserByID(1).toList()
        assertEquals(updatedUser, retrievedUser.first())
    }

    @Test
    fun deleteUser() = runTest {
        val user = User(1, "John Berlin", 0, 1)
        userRepository.insertUser(user)
        userRepository.deleteUser(user)
        val users = userRepository.allUsers.toList()
        assertFalse(users.contains(user))
    }
}