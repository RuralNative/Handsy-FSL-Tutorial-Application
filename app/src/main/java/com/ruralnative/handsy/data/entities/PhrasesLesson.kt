package com.ruralnative.handsy.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class used to represent a phrase lesson in the database.
 *
 * This class defines the structure of a phrase lesson in the database, including
 * fields for the lesson's ID, name, description, and media file. It uses annotations to map
 * these fields to the corresponding columns in the `phrases_lessons` table in the database.
 *
 * @property id The unique identifier for the phrase lesson.
 * @property lessonName The name of the phrase lesson.
 * @property lessonDescription The description of the phrase lesson.
 * @property lessonMediaFile The media file associated with the phrase lesson.
 *
 * @constructor Creates a new instance of PhrasesLesson.
 */
@Entity(tableName = "phrases_lessons")
data class PhrasesLesson (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "lesson_name") val lessonName: String,
    @ColumnInfo(name = "lesson_description") val lessonDescription: String,
    @ColumnInfo(name = "lesson_media_file") val lessonMediaFile: String
)
