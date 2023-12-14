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

    @Delete
    fun deleteUser(user: User)
}