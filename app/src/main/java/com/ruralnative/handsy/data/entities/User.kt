package com.ruralnative.handsy.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    //id, user_name, is_new_user, progression_level
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "is_new_user") val isNewUser: Int,
    @ColumnInfo(name = "progression_level") val progressionLevel: Int
)
