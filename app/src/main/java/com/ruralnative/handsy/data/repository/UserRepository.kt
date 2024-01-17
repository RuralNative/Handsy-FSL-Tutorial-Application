package com.ruralnative.handsy.data.repository

import androidx.annotation.WorkerThread
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.entities.User
import com.ruralnative.handsy.di.UserDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

@WorkerThread
class UserRepository {

    @Inject
    @UserDAO
    lateinit var dao: UserDao

    val allUsers: Flow<List<User>> = dao.selectAllUsers()

    suspend fun getUserByID(userID: Int): Flow<User> {
        return dao.selectUserById(userID)
    }

    suspend fun isThereAUser(): Flow<Boolean> {
        val numberOfUsers: Int = dao.countUsers().firstOrNull() ?: 0
        var isUserEmpty = true

        if (numberOfUsers == 0) {
            isUserEmpty = true
        } else if (numberOfUsers != 0) {
            isUserEmpty = false
        }

        return MutableStateFlow(isUserEmpty)
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