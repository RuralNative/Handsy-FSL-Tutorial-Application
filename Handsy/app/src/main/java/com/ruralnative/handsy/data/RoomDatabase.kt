package com.ruralnative.handsy.data

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Database(
    version = 1,
    entities = [
        User::class,
        AlphabetLessons::class,
        PhrasesLesson::class
    ]
)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun alphabetLessonsDao(): AlphabetLessonsDao
    abstract fun phrasesLessonDao(): PhrasesLessonsDao
}
