package com.ruralnative.handsy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ruralnative.handsy.data.entities.User

@Dao
interface UserDao {

    @Query("SELECT * from user WHERE id = :userID")
    fun selectUserById(userID: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("UPDATE user SET user_name = :userName WHERE id = :userID")
    fun updateUserName(userName: String?, userID: Int)

    @Query("UPDATE user SET is_new_user = :boolValue WHERE id = :userID")
    fun updateUserStatus(boolValue: Int, userID: Int)

    @Query("UPDATE user SET progression_level = :userLevel WHERE id = :userID")
    fun updateUserProgressionLevel(userLevel: Int, userID: Int)

    @Delete
    fun deleteUser(user: User)
}