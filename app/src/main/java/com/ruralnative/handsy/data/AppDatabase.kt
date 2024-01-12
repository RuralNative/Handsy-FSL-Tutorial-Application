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
import javax.inject.Inject

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

    @Inject
    lateinit var userDao: UserDao
    @Inject
    lateinit var alphabetLessonDao: AlphabetLessonDao
    @Inject
    lateinit var phrasesLessonDao: PhrasesLessonDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database.db"
                )
                .fallbackToDestructiveMigration()
                .createFromAsset("database.db")
                .build()
                .also {INSTANCE = it}
            }
        }
    }
}