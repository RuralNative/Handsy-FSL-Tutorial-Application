package com.ruralnative.handsy.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alphabet_lessons")
data class AlphabetLesson (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "lesson_name") val lessonName: String?,
    @ColumnInfo(name = "lesson_description") val lessonDescription: String?,
    @ColumnInfo(name = "lesson_media_file") val lessonMediaFile: String?
)