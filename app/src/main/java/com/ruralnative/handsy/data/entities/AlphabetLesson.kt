package com.ruralnative.handsy.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class used to represent an alphabet lesson in the database.
 *
 * This class defines the structure of an alphabet lesson in the database, including
 * fields for the lesson's ID, name, description, and media file. It uses annotations to map
 * these fields to the corresponding columns in the `alphabet_lessons` table in the database.
 *
 * @property id The unique identifier for the alphabet lesson.
 * @property lessonName The name of the alphabet lesson.
 * @property lessonDescription The description of the alphabet lesson.
 * @property lessonMediaFile The media file associated with the alphabet lesson.
 *
 * @constructor Creates a new instance of AlphabetLesson.
 */
@Entity(tableName = "alphabet_lessons")
data class AlphabetLesson (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "lesson_name") val lessonName: String,
    @ColumnInfo(name = "lesson_description") val lessonDescription: String,
    @ColumnInfo(name = "lesson_media_file") val lessonMediaFile: String
)
