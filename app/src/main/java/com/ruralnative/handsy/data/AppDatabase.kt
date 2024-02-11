package com.ruralnative.handsy.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.data.entities.PhrasesLesson
import com.ruralnative.handsy.data.entities.User
import dagger.Binds
import javax.inject.Inject
import javax.inject.Singleton

@Database(
    entities = [
        User::class,
        AlphabetLesson::class,
        PhrasesLesson::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun alphabetLessonDao(): AlphabetLessonDao
    abstract fun phrasesLessonDao(): PhrasesLessonDao
}