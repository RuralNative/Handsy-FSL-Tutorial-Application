package com.ruralnative.handsy.data.repository

import androidx.annotation.WorkerThread
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.entities.User
import com.ruralnative.handsy.di.UserDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@WorkerThread
class UserRepository @Inject constructor(
    private val dao : UserDao
) {

    val allUsers: Flow<List<User>> = dao.selectAllUsers()

    fun getUserByID(userID: Int): Flow<User> {
        return dao.selectUserById(userID)
    }

    fun isThereNoUser(): Flow<Boolean> = flow {
        val numberOfUsers: Int? = dao.countUsers().firstOrNull()
        var isUserEmpty = true

        if (numberOfUsers == 0) {
            isUserEmpty = true
        } else if (numberOfUsers != 0) {
            isUserEmpty = false
        }
        emit(isUserEmpty)
    }

    suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    suspend fun insertNewUser(id: Int, name: String) {
        val user = User(
            id,
            name,
            0,
            1
        )
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