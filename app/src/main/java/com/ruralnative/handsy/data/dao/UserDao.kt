package com.ruralnative.handsy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ruralnative.handsy.data.entities.User
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [User] class as an Interface.
 *
 * This interface defines methods for interacting with the `user` table in the database.
 * It includes methods for querying, inserting, updating, and deleting [User] objects.
 *
 * @constructor Creates a new instance of UserDao.
 */
@Dao
interface UserDao {

    /**
     * Returns a Flow of List of all [User] in the database.
     *
     * @return A Flow of List of all users.
     */
    @Query("SELECT * from user")
    fun selectAllUsers(): Flow<List<User>>

    /**
     * Returns a Flow of [User] with the given ID.
     *
     * @param userID the ID of the user to return.
     * @return A Flow of the user with the specified ID.
     */
    @Query("SELECT * from user WHERE id = :userID")
    fun selectUserById(userID: Int): Flow<User>

    /**
     * Returns a Flow of Int representing the current number of [User] in the database.
     *
     * @return A Flow of the count of users.
     */
    @Query("SELECT COUNT(*) from user")
    fun countUsers(): Flow<Int>

    /**
     * Inserts a [User] into the database.
     *
     * @param user the user to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * Updates a [User] in the database.
     *
     * @param user the user to update.
     */
    @Update
    suspend fun updateUser(user: User)

    /**
     * Updates the name of a [User] with a given ID.
     *
     * @param userName the new name of the user.
     * @param userID the ID of the user.
     */
    @Query("UPDATE user SET user_name = :userName WHERE id = :userID")
    suspend fun updateUserName(userName: String?, userID: Int)

    /**
     * Updates whether [User] is new or not with a given ID.
     *
     * @param boolValue the boolean value representing whether the user is new or not.
     * @param userID the ID of the user.
     */
    @Query("UPDATE user SET is_new_user = :boolValue WHERE id = :userID")
    suspend fun updateUserStatus(boolValue: Int, userID: Int)

    /**
     * Updates the progression level of a [User] with a given ID.
     *
     * @param userLevel the new progression level of the user.
     * @param userID the ID of the user.
     */
    @Query("UPDATE user SET progression_level = :userLevel WHERE id = :userID")
    suspend fun updateUserProgressionLevel(userLevel: Int, userID: Int)

    /**
     * Deletes a [User] from the database.
     *
     * @param user the user to delete.
     */
    @Delete
    suspend fun deleteUser(user: User)
}
