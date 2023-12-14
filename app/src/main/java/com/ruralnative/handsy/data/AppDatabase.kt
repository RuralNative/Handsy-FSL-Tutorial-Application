package com.ruralnative.handsy.data

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.ruralnative.handsy.data.dao.AlphabetLessonDao
import com.ruralnative.handsy.data.dao.PhrasesLessonDao
import com.ruralnative.handsy.data.dao.UserDao
import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.data.entities.PhrasesLesson
import com.ruralnative.handsy.data.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Database(
    version = 1,
    entities = [
        User::class,
        AlphabetLesson::class,
        PhrasesLesson::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    val databaseBuilder = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "database-name"
    ).build()
    Room.databaseBuilder(appContext, AppDatabase::class.java, "Sample.db")
    .createFromAsset("database/myapp.db")
    .build()
    abstract fun userDao(): UserDao
    abstract fun alphabetLessonsDao(): AlphabetLessonDao
    abstract fun phrasesLessonDao(): PhrasesLessonDao
}
{
}