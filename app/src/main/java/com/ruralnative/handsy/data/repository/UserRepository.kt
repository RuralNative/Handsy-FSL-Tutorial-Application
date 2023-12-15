package com.ruralnative.handsy.data.repository

import com.ruralnative.handsy.data.entities.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>

    fun getUserByID(userID: Int): Flow<User>

    fun insertUser(user: User)

    fun updateUser(user: User)

    fun updateUserNameWithID(userName: String?, userID: Int)

    fun updateUserStatusWithID(boolValue: Int, userID: Int)

    fun updateUserLevelWithID(userLevel: Int, userID: Int)

    fun deleteUser(user: User)
}