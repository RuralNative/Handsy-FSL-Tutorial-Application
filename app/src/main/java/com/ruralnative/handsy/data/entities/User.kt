package com.ruralnative.handsy.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class used to represent a user in the database.
 *
 * This class defines the structure of a user in the database, including
 * fields for the user's ID, name, status as a new user, and progression level.
 * It uses annotations to map these fields to the corresponding columns in the
 * `user` table in the database.
 *
 * @property id The unique identifier for the user.
 * @property userName The name of the user.
 * @property isNewUser Indicates whether the user is new (1) or not (0).
 * @property progressionLevel The progression level of the user.
 *
 * @constructor Creates a new instance of User.
 */
@Entity(
    tableName = "user"
)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "is_new_user") val isNewUser: Int,
    @ColumnInfo(name = "progression_level") val progressionLevel: Int
)
