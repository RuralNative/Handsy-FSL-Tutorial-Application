package com.ruralnative.handsy.data.repository

import androidx.annotation.WorkerThread
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.entities.User
import kotlinx.coroutines.flow.Flow

@Suppress("RedundantSuspendModifier")
@WorkerThread
class UserRepository(private val dao: UserDao) {
    val allUsers: Flow<List<User>> = dao.selectAllUsers()

    suspend fun getUserByID(userID: Int): Flow<User> {
        return dao.selectUserById(userID)
    }

    suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        dao.updateUser(user)
    }

    suspend fun updateUserNameWithID(userName: String?, userID: Int) {
        dao.updateUserName(userName, userID)
    }

    suspend fun updateUserStatusWithID(boolValue: Int, userID: Int) {
        dao.updateUserStatus(boolValue, userID)
    }

    suspend fun updateUserLevelWithID(userLevel: Int, userID: Int) {
        dao.updateUserProgressionLevel(userLevel, userID)
    }

    suspend fun deleteUser(user: User) {
        dao.deleteUser(user)
    }
}